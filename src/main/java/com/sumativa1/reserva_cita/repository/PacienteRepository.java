package com.sumativa1.reserva_cita.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumativa1.reserva_cita.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{

}
