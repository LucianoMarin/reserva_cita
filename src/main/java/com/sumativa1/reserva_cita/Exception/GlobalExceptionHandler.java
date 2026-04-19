package com.sumativa1.reserva_cita.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.sumativa1.reserva_cita.controller.CitaDuplicateResourceException;
import com.sumativa1.reserva_cita.controller.CitaNotFoundException;



@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CitaNotFoundException.class)
    public ResponseEntity<String> handleNotFound(CitaNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(CitaDuplicateResourceException.class)
    public ResponseEntity<String> handleDuplicate(CitaDuplicateResourceException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handle404(NoHandlerFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ruta no encontrada");
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<String> handleJpaValidation(jakarta.validation.ConstraintViolationException ex) {

        String msg = ex.getConstraintViolations()
                .stream()
                .findFirst()
                .map(v -> v.getMessage())
                .orElse("Error de validación");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(msg);
    }



        @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntime(RuntimeException ex) {

        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

}
