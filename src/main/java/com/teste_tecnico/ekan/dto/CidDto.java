package com.teste_tecnico.ekan.dto;

import jakarta.validation.constraints.*;

public record CidDto(

@NotNull(message = "Code is required")
String id, 

@NotNull(message = "Name is required")
String name) {

}
