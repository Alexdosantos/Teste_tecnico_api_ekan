package com.teste_tecnico.ekan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import com.teste_tecnico.ekan.dto.AppointmentDTO;
import com.teste_tecnico.ekan.entity.Appointment;
import com.teste_tecnico.ekan.infra.exceptions.ResourceNotFoundException;
import com.teste_tecnico.ekan.service.AppointmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
@Tag(name = "Atendimento", description = "Endpoints para gerenciar atendimentos")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Operation(summary = "Cria um novo atendimento", description = "Cria um atendimento associado a um paciente e uma condição", responses = {
            @ApiResponse(responseCode = "200", description = "Agendamento criado com sucesso", content = @Content(schema = @Schema(implementation = Appointment.class))),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/create/{patientId}")
    public Appointment create(@Valid @RequestBody AppointmentDTO appointmentDTO, @PathVariable Long patientId) {
        return appointmentService.create(appointmentDTO, patientId);
    }

    @Operation(summary = "Lista todos os atendimentos", description = "Retorna uma lista de todos os atendimentos", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de atendimentos retornada com sucesso", content = @Content(schema = @Schema(implementation = Appointment.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum atendimento encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public List<Appointment> findAll() {
        return appointmentService.findAll();
    }

    @Operation(summary = "Busca um atendimento por ID", description = "Busca um atendimento por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Atendimento encontrado com sucesso", content = @Content(schema = @Schema(implementation = Appointment.class))),
            @ApiResponse(responseCode = "404", description = "Atendimento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public Appointment findById(@PathVariable Long id) {
        return appointmentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com ID: " + id));
    }

    @Operation(summary = "Busca agendamentos por ID do paciente", description = "Busca agendamentos por ID do paciente", responses = {
            @ApiResponse(responseCode = "200", description = "Agendamentos encontrados com sucesso", content = @Content(schema = @Schema(implementation = Appointment.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum agendamento encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/patient/{patientId}")
    public List<Appointment> findByPatientId(@PathVariable Long patientId) {
        return appointmentService.findByPatientId(patientId);
    }

    @Operation(summary = "Deleta um atendimento por ID", description = "Deleta um atendimento por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Atendimento deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Atendimento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        appointmentService.delete(id);
    }

    @Operation(summary = "Atualiza um atendimento por ID", description = "Atualiza um atendimento por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Atendimento atualizado com sucesso", content = @Content(schema = @Schema(implementation = Appointment.class))),
            @ApiResponse(responseCode = "404", description = "Atendimento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PatchMapping("/{id}")
    public Appointment update(@PathVariable Long id, @RequestBody AppointmentDTO appointmentDTO) {
        return appointmentService.update(id, appointmentDTO);
    }

    @Operation(summary = "Envia o último atendimento por e-mail", description = "Envia o último atendimento por e-mail", responses = {
            @ApiResponse(responseCode = "200", description = "Último atendimento enviado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/{patientId}/send-last")
    public ResponseEntity<String> sendLastAppointment(@PathVariable Long patientId) {
        appointmentService.sendLastAppointment(patientId);
        return ResponseEntity.ok("Último atendimento enviado com sucesso para rdoni.ekan@iamspe.sp.gov.br");
    }
}
