package com.teste_tecnico.ekan.config;

import java.text.SimpleDateFormat;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste_tecnico.ekan.entity.Appointment;

public class FhirConverter {

    public static String convertToFhirEncounterJson(Appointment appointment) {
        try {
            Map<String, Object> encounter = new LinkedHashMap<>();
            encounter.put("resourceType", "Encounter");
            encounter.put("id", appointment.getId());
            encounter.put("status", "finished");

            Map<String, String> subject = new HashMap<>();
            subject.put("reference", "Patient/" + appointment.getPatient().getId());
            subject.put("display", appointment.getPatient().getName());
            encounter.put("subject", subject);

            Map<String, Object> period = new HashMap<>();
            if (appointment.getDateOfAttendance() != null) {
                String formattedDate = formatDate(appointment.getDateOfAttendance());
                period.put("start", formattedDate);
            }
            encounter.put("period", period);

            if (appointment.getDiagnostic() != null) {
                Map<String, Object> diagnosis = new HashMap<>();
                Map<String, String> conditionRef = new HashMap<>();
                conditionRef.put("reference", "Condition/" + appointment.getDiagnostic().getId());
                conditionRef.put("display", appointment.getDiagnostic().getCid().getName());
                diagnosis.put("condition", conditionRef);
                encounter.put("diagnosis", List.of(diagnosis));
            }

            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(encounter);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter para FHIR Encounter JSON", e);
        }
    }

    private static String formatDate(Object dateObj) {
        try {
            if (dateObj instanceof java.util.Date date) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
                return sdf.format(date);
            } else if (dateObj instanceof java.time.LocalDateTime dateTime) {
                return dateTime.toString();
            } else if (dateObj instanceof java.time.LocalDate date) {
                return date.atStartOfDay().toString();
            } else if (dateObj instanceof java.time.OffsetDateTime offset) {
                return offset.toString();
            } else {
                return String.valueOf(dateObj);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao formatar data no FHIR Converter", e);
        }
    }
}
