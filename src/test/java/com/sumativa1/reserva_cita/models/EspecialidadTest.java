package com.sumativa1.reserva_cita.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sumativa1.reserva_cita.model.Especialidad;

public class EspecialidadTest {



    @Test
    void testConstructorEspecialidad(){
        Especialidad especialidad=new Especialidad(1L,"Cardiologia");

        
        assertEquals(1L, especialidad.getId());
        assertEquals("Cardiologia", especialidad.getNombreEspecialidad());


    }

    @Test
    void testEspecialidadGetterAndSetter(){
    Especialidad especialidad = new Especialidad();

    especialidad.setId(1L);
    especialidad.setNombreEspecialidad("Cardiologia");

    assertEquals(1L, especialidad.getId());
    assertEquals("Cardiologia", especialidad.getNombreEspecialidad());

    }
}
