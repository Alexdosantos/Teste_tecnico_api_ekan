package com.teste_tecnico.ekan.dto;

import jakarta.validation.constraints.*;

public record PatientDTO(
                Long id,

                @NotBlank(message = "Name is required") String name,

                @Email(message = "Email is required") String email,

                @NotNull(message = "Birth date is required") String birthDate,

                @NotNull(message = "Gender is required") String gender,
                String phone,
                String address,
                String cpf,
                String city,
                String state,
                String zipCode,
                String country,
                String complement,
                String bloodType

) {
}
