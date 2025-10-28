package com.teste_tecnico.ekan.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
    String message,
    String error,   
    String endpoint,    
    int status,
    LocalDateTime timestamp
) {}