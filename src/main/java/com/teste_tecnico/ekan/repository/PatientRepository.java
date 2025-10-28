package com.teste_tecnico.ekan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste_tecnico.ekan.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findById(Long id);
    Optional<Patient> findByEmail(String email);
    Optional<Patient> findByCpf(String cpf);

}
