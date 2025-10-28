package com.teste_tecnico.ekan.dto;

import jakarta.validation.constraints.NotBlank;

public record DiagnosticDTO(
        @NotBlank(message = "Note is required") String note,

        @NotBlank(message = "Cid ID is required") String cidId

) {
}
