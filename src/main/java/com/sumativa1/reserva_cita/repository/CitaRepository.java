package com.sumativa1.reserva_cita.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumativa1.reserva_cita.model.Cita;

public interface CitaRepository extends JpaRepository <Cita, Long> {

    boolean existsByFechaCitacionAndHoraCitacion(LocalDate fechaCitacion, LocalTime horaCitacion);

}
