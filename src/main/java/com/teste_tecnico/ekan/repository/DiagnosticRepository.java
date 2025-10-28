package com.teste_tecnico.ekan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.teste_tecnico.ekan.entity.Diagnostic;

public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> {

}
