package com.sumativa1.reserva_cita.controller;


/* clase excepcion para citas duplicados */
public class CitaDuplicateResourceException extends RuntimeException {

    public CitaDuplicateResourceException(String message) {
        super(message);

    }
}
