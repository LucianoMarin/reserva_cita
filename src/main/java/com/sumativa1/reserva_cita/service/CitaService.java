package com.sumativa1.reserva_cita.service;

import java.time.LocalTime;
import java.util.List;


import com.sumativa1.reserva_cita.model.Cita;


public interface CitaService {

    Cita agregarCita(Cita cita);
    void eliminarCita(Long id);
    Cita mostrarCitaId(Long id);
    List <Cita> mostrarCitas();
    Cita modificarCita(Cita cita, Long id);
    String actualizarEstadoCitas();
    List <LocalTime> mostrarHorarioDisponibleHoy();
    List <LocalTime> mostrarHorariosPorFecha(String fecha);
    
}
