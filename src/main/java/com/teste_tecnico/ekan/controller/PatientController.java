package com.teste_tecnico.ekan.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste_tecnico.ekan.dto.PatientDTO;
import com.teste_tecnico.ekan.dto.PatientUpdateDTO;
import com.teste_tecnico.ekan.entity.Patient;
import com.teste_tecnico.ekan.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
@Tag(name = "Pacientes", description = "Endpoints para gerenciar pacientes")
public class PatientController {

    private final PatientService patientService;

    @Operation(summary = "Cria um novo paciente", description = "Cria um paciente", responses = {
            @ApiResponse(responseCode = "200", description = "Paciente criado com sucesso", content = @Content(schema = @Schema(implementation = Patient.class))),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "409", description = "Paciente já existe"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public Patient createPatient(@Valid @RequestBody PatientDTO patientDTO) {
        return patientService.createPatient(patientDTO);
    }

    @Operation(summary = "Lista todos os pacientes", description = "Retorna uma lista de todos os pacientes", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de pacientes retornada com sucesso", content = @Content(schema = @Schema(implementation = Patient.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum paciente encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public List<Patient> findAll() {
        return patientService.findAll();
    }

    @Operation(summary = "Busca um paciente por ID", description = "Busca um paciente por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado com sucesso", content = @Content(schema = @Schema(implementation = Patient.class))),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public Patient findById(@PathVariable Long id) {
        return patientService.findById(id);
    }

    @Operation(summary = "Deleta um paciente por ID", description = "Deleta um paciente por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Paciente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        patientService.delete(id);
    }

    @Operation(summary = "Atualiza um paciente por ID", description = "Atualiza um paciente por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Paciente atualizado com sucesso", content = @Content(schema = @Schema(implementation = Patient.class))),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PatchMapping("/{id}")
    public Patient update(@PathVariable Long id, @RequestBody PatientUpdateDTO patientUpdateDTO) {
        return patientService.update(id, patientUpdateDTO);
    }

}
