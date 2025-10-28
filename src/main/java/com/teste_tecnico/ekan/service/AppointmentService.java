package com.teste_tecnico.ekan.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.teste_tecnico.ekan.config.FhirConverter;
import com.teste_tecnico.ekan.dto.AppointmentDTO;
import com.teste_tecnico.ekan.dto.DiagnosticDTO;
import com.teste_tecnico.ekan.entity.Appointment;
import com.teste_tecnico.ekan.entity.Cid;
import com.teste_tecnico.ekan.entity.Diagnostic;
import com.teste_tecnico.ekan.entity.Patient;
import com.teste_tecnico.ekan.repository.AppointmentRespository;
import com.teste_tecnico.ekan.repository.DiagnosticRepository;
import com.teste_tecnico.ekan.repository.CidRepository;
import com.teste_tecnico.ekan.infra.exceptions.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRespository appointmentRespository;
    private final PatientService patientService;
    private final CidRepository cidRepository;
    private final DiagnosticRepository diagnosticRepository;
    private final EmailService emailService;

    public Appointment create(AppointmentDTO appointmentDTO, Long patientId) {
        // Busca o paciente
        Patient patient = patientService.findById(patientId);
        if (patient == null) {
            throw new ResourceNotFoundException("Patient not found with ID: " + patientId);
        }

        // Cria a Condition e associa o CID
        DiagnosticDTO conditionDTO = appointmentDTO.condition();
        Cid cid = cidRepository.findById(conditionDTO.cidId())
                .orElseThrow(() -> new ResourceNotFoundException("CID not found with ID: " + conditionDTO.cidId()));

        Diagnostic condition = Diagnostic.builder()
                .note(conditionDTO.note())
                .cid(cid)
                .createdAt(new Date())
                .build();

        diagnosticRepository.save(condition);

        // Cria o Appointment
        Appointment appointment = Appointment.builder()
                .dateOfAttendance(appointmentDTO.dateOfAttendance())
                .createdAt(new Date())
                .diagnostic(condition)
                .patient(patient)
                .build();

        emailService.sendEmail(
                patient.getEmail(),
                "Atendimento realizado com sucesso!",
                "Olá " + patient.getName() + ", seu atendimento foi registrado com sucesso!\n\n" +
                        "Horário do atendimento: " + appointmentDTO.dateOfAttendance() + "\n\n" +
                        "CID: " + conditionDTO.cidId() + "\n\n" +
                        "Observação: " + conditionDTO.note() + "\n\n" +
                        "Atenciosamente,\n\n" +
                        "Equipe de Atendimento");

        return appointmentRespository.save(appointment);
    }

    public List<Appointment> findAll() {
        return appointmentRespository.findAll();
    }

    public Optional<Appointment> findById(Long id) {
        return appointmentRespository.findById(id);
    }

    public void delete(Long id) {
        appointmentRespository.deleteById(id);
    }

    public Appointment update(Long appointmentId, AppointmentDTO appointmentDTO) {
        Appointment appointmentUpdate = appointmentRespository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + appointmentId));

        if (appointmentDTO.dateOfAttendance() != null) {
            appointmentUpdate.setDateOfAttendance(appointmentDTO.dateOfAttendance());
        }
        DiagnosticDTO conditionDTO = appointmentDTO.condition();
        if (conditionDTO != null) {
            Diagnostic condition = appointmentUpdate.getDiagnostic();
            if (conditionDTO.note() != null) {
                condition.setNote(conditionDTO.note());
            }
            if (conditionDTO.cidId() != null) {
                Cid cid = cidRepository.findById(conditionDTO.cidId())
                        .orElseThrow(
                                () -> new ResourceNotFoundException("CID not found with ID: " + conditionDTO.cidId()));
                condition.setCid(cid);
            }
            condition.setUpdatedAt(new Date());
            diagnosticRepository.save(condition);
        }

        appointmentUpdate.setUpdatedAt(new Date());

        return appointmentRespository.save(appointmentUpdate);
    }

    public List<Appointment> findByPatientId(Long patientId) {
        Patient patient = patientService.findById(patientId);
        if (patient == null) {
            throw new ResourceNotFoundException("Patient not found with ID: " + patientId);
        }
        return appointmentRespository.findByPatientId(patientId);
    }

    public void sendLastAppointment(Long patientId) {
        Appointment lastAppointment = appointmentRespository.findTopByPatientIdOrderByCreatedAtDesc(patientId);

        if (lastAppointment == null) {
            throw new ResourceNotFoundException("No appointment found for patient ID: " + patientId);
        }

        String fhirJson = FhirConverter.convertToFhirEncounterJson(lastAppointment);

        emailService.sendEmail(
                "rdoni.ekan@iamspe.sp.gov.br",
                "Último Atendimento do Paciente: " + lastAppointment.getPatient().getName(),
                "Segue o último atendimento:\n\n" + fhirJson);
    }

}
