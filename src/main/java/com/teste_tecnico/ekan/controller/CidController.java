package com.teste_tecnico.ekan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste_tecnico.ekan.entity.Cid;
import com.teste_tecnico.ekan.service.CidService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cid")
@RequiredArgsConstructor
@Tag(name = "CIDs", description = "Endpoints para gerenciar CIDs")
public class CidController {

    private final CidService cidService;

    @Operation(summary = "Cria um novo CID", description = "Cria um novo CID", responses = {
            @ApiResponse(responseCode = "200", description = "CID criado com sucesso", content = @Content(schema = @Schema(implementation = Cid.class))),
            @ApiResponse(responseCode = "404", description = "CID não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public Cid save(@RequestBody Cid cid) {
        return cidService.save(cid);
    }

    @Operation(summary = "Lista todos os CIDs", description = "Retorna uma lista de todos os CIDs", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de CIDs retornada com sucesso", content = @Content(schema = @Schema(implementation = Cid.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum CID encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public List<Cid> findAll() {
        return cidService.findAll();
    }

    @Operation(summary = "Busca um CID por ID", description = "Busca um CID por ID", responses = {
            @ApiResponse(responseCode = "200", description = "CID encontrado com sucesso", content = @Content(schema = @Schema(implementation = Cid.class))),
            @ApiResponse(responseCode = "404", description = "CID não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public Optional<Cid> findById(@PathVariable String id) {
        return cidService.findById(id);
    }

    @Operation(summary = "Deleta um CID por ID", description = "Deleta um CID por ID", responses = {
            @ApiResponse(responseCode = "200", description = "CID deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "CID não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")    
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        cidService.delete(id);
    }

    @Operation(summary = "Atualiza um CID por ID", description = "Atualiza um CID por ID", responses = {
            @ApiResponse(responseCode = "200", description = "CID atualizado com sucesso", content = @Content(schema = @Schema(implementation = Cid.class))),
            @ApiResponse(responseCode = "404", description = "CID não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public Cid update(@PathVariable String id, @RequestBody Cid cid) {
        return cidService.update(id, cid);
    }
}
