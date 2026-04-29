package com.sumativa1.reserva_cita.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sumativa1.reserva_cita.model.Especialidad;
import com.sumativa1.reserva_cita.model.Medico;

public class MedicoTest {


    @Test
    void testConstructorMedico(){

        Especialidad especialidad=new Especialidad(1L,"Cirugia");
        Medico medico=new Medico(1L,"med00001","9862879-7",
        "Mario","Marin","Reyes",especialidad);
        
        assertEquals(1L, medico.getId());
        assertEquals("med00001", medico.getCodigoMedico());
        assertEquals("9862879-7", medico.getRut());
        assertEquals("Mario", medico.getNombre());
        assertEquals("Marin", medico.getApellido_paterno());
        assertEquals("Reyes", medico.getApellido_materno());
        assertEquals(especialidad, medico.getEspeciadad());
        
    }

    @Test
    void testMedicoGetterAndSetter(){
        Medico medico=new Medico();
        Especialidad especialidad=new Especialidad(1L,"Medico General");
        
        medico.setId(1L);
        medico.setCodigoMedico("med000001");
        medico.setRut("9862879-7");
        medico.setNombre("Mario");
        medico.setApellido_paterno("Marin");
        medico.setApellido_materno("Reyes");
        medico.setEspeciadad(especialidad);

        assertEquals(1L, medico.getId());
        assertEquals("med000001", medico.getCodigoMedico());
        assertEquals("9862879-7", medico.getRut());
        assertEquals("Mario", medico.getNombre());
        assertEquals("Marin", medico.getApellido_paterno());
        assertEquals("Reyes", medico.getApellido_materno());
        assertEquals(especialidad, medico.getEspeciadad());


    }


}
