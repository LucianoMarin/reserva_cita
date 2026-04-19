package com.sumativa1.reserva_cita.controller;

/* clase excepcion citas no encontrado */
public class CitaNotFoundException extends RuntimeException{

   public CitaNotFoundException(String message) {
        super(message);
    }


}
