package com.teste_tecnico.ekan.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.teste_tecnico.ekan.dto.DiagnosticDTO;
import com.teste_tecnico.ekan.entity.Cid;
import com.teste_tecnico.ekan.entity.Diagnostic;
import com.teste_tecnico.ekan.repository.DiagnosticRepository;
import com.teste_tecnico.ekan.infra.exceptions.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiagnosticService {

    private final DiagnosticRepository conditionRepository;
    private final CidService cidService;

    public Diagnostic createCondition(DiagnosticDTO conditionDTO) {
        Cid cid = cidService.findById(conditionDTO.cidId())
                .orElseThrow(() -> new ResourceNotFoundException("Cid not found"));

        Diagnostic condition = Diagnostic.builder()
                .note(conditionDTO.note())
                .cid(cid)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        return conditionRepository.save(condition);
    }

}
