package com.teste_tecnico.ekan.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.teste_tecnico.ekan.dto.ErrorResponseDTO;
import com.teste_tecnico.ekan.infra.exceptions.DuplicateResourceException;
import com.teste_tecnico.ekan.infra.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private String getCurrentEndpoint(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolation(DataIntegrityViolationException ex,
            HttpServletRequest request) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                "Violation of data integrity",
                ex.getMostSpecificCause().getMessage(),
                getCurrentEndpoint(request),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponseDTO> handleResponseStatus(ResponseStatusException ex,
            HttpServletRequest request) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                ex.getReason(),
                ex.getClass().getSimpleName(),
                getCurrentEndpoint(request),
                ex.getStatusCode().value(),
                LocalDateTime.now());
        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFound(ResourceNotFoundException ex,
            HttpServletRequest request) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                "Resource not found",
                ex.getMessage(),
                getCurrentEndpoint(request),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponseDTO> handleDuplicateResource(DuplicateResourceException ex,
            HttpServletRequest request) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                "Resource duplicated",
                ex.getMessage(),
                getCurrentEndpoint(request),
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex, HttpServletRequest request) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                "Internal server error",
                ex.getMessage(),
                getCurrentEndpoint(request),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now());
        logger.error("Uncaught error: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}