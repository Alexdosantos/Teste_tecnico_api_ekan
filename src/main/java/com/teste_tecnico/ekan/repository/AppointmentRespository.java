package com.teste_tecnico.ekan.repository;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste_tecnico.ekan.entity.Appointment;

public interface AppointmentRespository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findById(Long id);

    Appointment findByDateOfAttendance(LocalDateTime dateOfAttendance);

    List<Appointment> findByPatientId(Long patientId);

    Appointment findTopByPatientIdOrderByCreatedAtDesc(Long patientId);

}
