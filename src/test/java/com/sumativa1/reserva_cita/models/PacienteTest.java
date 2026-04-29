package com.sumativa1.reserva_cita.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sumativa1.reserva_cita.model.Paciente;

public class PacienteTest {


    @Test
    void testConstructorPaciente(){

        Paciente paciente=new Paciente(1L,"18775116-0","Luciano","Marin");
        
        assertEquals(1L, paciente.getId());
        assertEquals("18775116-0", paciente.getRut());
        assertEquals("Luciano", paciente.getNombre());
        assertEquals("Marin", paciente.getApellido());

    }


    @Test
    void pacienteGetterAndSetter(){

        Paciente paciente=new Paciente();
        paciente.setId(1L);
        paciente.setRut("18775116-0");
        paciente.setNombre("Luciano");
        paciente.setApellido("Marin");


        assertEquals(1L, paciente.getId());
        assertEquals("18775116-0", paciente.getRut());
        assertEquals("Luciano", paciente.getNombre());
        assertEquals("Marin", paciente.getApellido());

    }

}
