package com.teste_tecnico.ekan.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.teste_tecnico.ekan.dto.PatientDTO;
import com.teste_tecnico.ekan.dto.PatientUpdateDTO;
import com.teste_tecnico.ekan.entity.Patient;
import com.teste_tecnico.ekan.infra.exceptions.DuplicateResourceException;
import com.teste_tecnico.ekan.infra.exceptions.ResourceNotFoundException;
import com.teste_tecnico.ekan.repository.PatientRepository;
import com.teste_tecnico.ekan.mappers.GenericMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final GenericMapper<PatientUpdateDTO, Patient> baseMapper;

    // CREATE
    public Patient createPatient(PatientDTO patientDTO) {
        Optional<Patient> email = patientRepository.findByEmail(patientDTO.email());
        Optional<Patient> cpf = patientRepository.findByCpf(patientDTO.cpf());
        if (email.isPresent() || cpf.isPresent()) {
            throw new DuplicateResourceException("Patient already exists");
        }

        Patient patient = new Patient();
        patient.setName(patientDTO.name());
        patient.setEmail(patientDTO.email());
        patient.setPhone(patientDTO.phone());
        patient.setCpf(patientDTO.cpf());
        patient.setAddress(patientDTO.address());
        patient.setCity(patientDTO.city());
        patient.setState(patientDTO.state());
        patient.setZipCode(patientDTO.zipCode());
        patient.setGender(patientDTO.gender());
        patient.setBirthDate(patientDTO.birthDate());
        patient.setCreatedAt(new Date());
        patient.setUpdatedAt(new Date());

        return patientRepository.save(patient);
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient findById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + id));
    }

    public String delete(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Patient not found with ID: " + id);
        }
        patientRepository.deleteById(id);
        return "Patient deleted successfully";
    }

    public Patient update(Long id, PatientUpdateDTO dto) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + id));

        baseMapper.updateEntityFromDto(dto, existingPatient);

        existingPatient.setUpdatedAt(new Date());

        return patientRepository.save(existingPatient);
    }

}
