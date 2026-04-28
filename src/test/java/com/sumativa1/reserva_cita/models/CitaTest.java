package com.sumativa1.reserva_cita.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import com.sumativa1.reserva_cita.model.Cita;
import com.sumativa1.reserva_cita.model.Especialidad;
import com.sumativa1.reserva_cita.model.Medico;
import com.sumativa1.reserva_cita.model.Paciente;

public class CitaTest {

@Test

void TestConstructor(){

//TESTEAMOS EL CONSTRUCTOR PARAMETRIZADO.
   
    Paciente paciente =  new Paciente("18775116-0","Luciano","Marin");
    Especialidad especialidad= new Especialidad("Cardiologia");
    Medico medico = new Medico("MED000001", "19332143-2", "Alberto", "Cortez", "Cortez",especialidad);

    Cita cita=new Cita(100L,paciente,"CONFIRMADO",medico,
    LocalTime.of(10,00),
    LocalDate.now(),LocalDate.now());

    assertEquals(100L, cita.getId());
    assertEquals(paciente, cita.getPaciente());
    assertEquals("CONFIRMADO", cita.getEstado());
    assertEquals(medico, cita.getMedico());
    assertEquals(LocalTime.of(10,00),cita.getHoraCitacion());
    assertEquals(LocalDate.now(),cita.getFechaCitacion());
    assertEquals(LocalDate.now(),cita.getFechaConfirmacion());
}



void TestGetterAndSetter(){

}






}
