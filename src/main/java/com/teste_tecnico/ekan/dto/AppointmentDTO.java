package com.teste_tecnico.ekan.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record AppointmentDTO(

                @NotNull(message = "Date of attendance is required") LocalDateTime dateOfAttendance,
                DiagnosticDTO condition

) {

}
