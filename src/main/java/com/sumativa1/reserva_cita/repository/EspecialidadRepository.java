package com.sumativa1.reserva_cita.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumativa1.reserva_cita.model.Especialidad;

public interface EspecialidadRepository extends JpaRepository <Especialidad,Long> {


    Optional<Especialidad> findBynombreEspecialidad(String nombreEspecialidad);
}
