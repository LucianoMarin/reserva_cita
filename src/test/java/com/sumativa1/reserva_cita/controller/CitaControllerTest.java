package com.sumativa1.reserva_cita.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumativa1.reserva_cita.model.Cita;
import com.sumativa1.reserva_cita.model.Especialidad;
import com.sumativa1.reserva_cita.model.Medico;
import com.sumativa1.reserva_cita.model.Paciente;
import com.sumativa1.reserva_cita.repository.CitaRepository;
import com.sumativa1.reserva_cita.service.CitaService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

@WebMvcTest(CitaController.class)
public class CitaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CitaRepository citaRepository;

    @MockBean
    private CitaService citaService;

    @Autowired
    private ObjectMapper mapper;

    private Cita cita;

    @BeforeEach
    void setUp() {

        Especialidad especialidad = new Especialidad(1L, "Cirugia");
        Medico medico = new Medico(1L, "med00001", "9862879-7",
                "Mario", "Marin", "Reyes", especialidad);

        Paciente paciente = new Paciente(1L, "18775116-0", "Luciano", "Marin");

        cita = new Cita();

        cita.setId(1L);
        cita.setMedico(medico);
        cita.setEstado("PENDIENTE");
        cita.setPaciente(paciente);
        cita.setHoraCitacion(LocalTime.of(8, 00));
        cita.setFechaCitacion(LocalDate.now());
        cita.setFechaConfirmacion(LocalDate.now());

    }

    @Test
    void testIngresarCita() throws Exception {
        when(citaService.agregarCita(any(Cita.class))).thenReturn(cita);

        mockMvc.perform(post("/citas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(cita)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(cita)));
    }

    @Test
    void testEliminarCita() throws Exception {

        mockMvc.perform(delete("/citas/1"))
                .andExpect(status().isOk());

        verify(citaService).eliminarCita(1L);
    }

    @Test
    void testMostrarCitaPorId() throws Exception {
        when(citaService.mostrarCitaId(1L))
                .thenReturn(cita);

        mockMvc.perform(get("/citas/1"))
                .andExpect(status().isOk());

        verify(citaService).mostrarCitaId(1L);

    }

    @Test
    void testMostrarCitas() throws Exception {

        when(citaService.mostrarCitas())
                .thenReturn(List.of(cita));

        mockMvc.perform(get("/citas"))
                .andExpect(status().isOk());

        verify(citaService).mostrarCitas();
    }

    @Test
    void testActualizarCita() throws Exception {

        when(citaService.modificarCita(any(Cita.class), eq(1L)))
                .thenReturn(cita);

        mockMvc.perform(put("/citas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(cita)))
                .andExpect(status().isOk());

        verify(citaService).modificarCita(any(Cita.class), eq(1L));
    }

    @Test
    void testActualizarEstadosCita() throws Exception {

        mockMvc.perform(put("/citas/actualizar-estados-cita"))
                .andExpect(status().isOk());

        verify(citaService).actualizarEstadoCitas();
    }

    @Test
    void testDisponibilidadHoy() throws Exception {

        when(citaService.mostrarHorarioDisponibleHoy())
                .thenReturn(List.of(LocalTime.of(8, 0)));

        mockMvc.perform(get("/citas/disponibilidad-hoy"))
                .andExpect(status().isOk());

        verify(citaService).mostrarHorarioDisponibleHoy();
    }

    @Test
    void testDisponibilidadPorFecha() throws Exception {

        String fecha = "2026-04-29";

        when(citaService.mostrarHorariosPorFecha(fecha))
                .thenReturn(List.of(LocalTime.of(10, 0)));

        mockMvc.perform(get("/citas/disponibilidad-fecha/" + fecha))
                .andExpect(status().isOk());

        verify(citaService).mostrarHorariosPorFecha(fecha);
    }

}
