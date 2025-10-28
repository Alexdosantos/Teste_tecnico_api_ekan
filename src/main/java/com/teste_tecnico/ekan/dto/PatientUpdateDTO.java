package com.teste_tecnico.ekan.dto;

public record PatientUpdateDTO(
        String name,
        String email,
        String cpf,
        String phone,
        String address,
        String bloodType,
        String country,
        String complement,
        String city,
        String state,
        String zipCode,
        String gender,
        String birthDate) {

}
