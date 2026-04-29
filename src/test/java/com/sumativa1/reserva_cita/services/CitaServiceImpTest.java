package com.sumativa1.reserva_cita.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sumativa1.reserva_cita.model.Cita;
import com.sumativa1.reserva_cita.model.Especialidad;
import com.sumativa1.reserva_cita.model.Medico;
import com.sumativa1.reserva_cita.model.Paciente;
import com.sumativa1.reserva_cita.repository.CitaRepository;
import com.sumativa1.reserva_cita.repository.EspecialidadRepository;
import com.sumativa1.reserva_cita.repository.MedicoRepository;
import com.sumativa1.reserva_cita.repository.PacienteRepository;
import com.sumativa1.reserva_cita.service.CitaServiceImp;

@ExtendWith(MockitoExtension.class)
public class CitaServiceImpTest {

    @Mock
    private CitaRepository citaRepository;

    @Mock
    private MedicoRepository medRepository;

    @Mock
    private EspecialidadRepository espRepository;

    @Mock
    private PacienteRepository pacRepository;

    @InjectMocks
    private CitaServiceImp service;

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
    void testAgregarNuevaCita() {
        when(citaRepository.save(cita)).thenReturn(cita);
        assertEquals(cita, service.agregarCita(cita));
        verify(citaRepository).save(cita);

    }

    @Test
    void testELiminarCita() {
        when(citaRepository.existsById(1L)).thenReturn(true);
        service.eliminarCita(1L);
        verify(citaRepository).deleteById(1L);
    }

    @Test
    void testMostrarPorIdCita() {
        when(citaRepository.findById(1L)).thenReturn(Optional.of(cita));
        Cita resultado = service.mostrarCitaId(1L);

        assertEquals(cita, resultado);

        verify(citaRepository).findById(1L);

    }

    @Test
    void testObtenerTodasLasCitas() {

        List<Cita> expected = Arrays.asList(cita);
        when(citaRepository.findAll()).thenReturn(expected);
        assertEquals(expected, service.mostrarCitas());

    }

    @Test
    void testActualizarCita() {
        when(citaRepository.findById(1L)).thenReturn(Optional.of(cita));

        when(medRepository.findById(1L))
                .thenReturn(Optional.of(cita.getMedico()));

        when(pacRepository.findById(1L))
                .thenReturn(Optional.of(cita.getPaciente()));

        when(citaRepository.save(any(Cita.class)))
                .thenReturn(cita);

        when(citaRepository.save(cita)).thenReturn(cita);

        Cita resultado = service.modificarCita(cita, 1L);

        assertEquals(cita, resultado);

        verify(citaRepository).findById(1L);
        verify(pacRepository).findById(1L);
        verify(medRepository).findById(1L);
        verify(citaRepository).save(any(Cita.class));
        verify(citaRepository).save(cita);

    }

    @Test
    void actualizarEstadoCita() {
        Cita cita = new Cita();
        cita.setFechaCitacion(LocalDate.now());
        cita.setFechaConfirmacion(LocalDate.now());

        List<Cita> lista = List.of(cita);

        when(citaRepository.findAll()).thenReturn(lista);

        when(citaRepository.save(any(Cita.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        service.actualizarEstadoCitas();

        assertEquals("CONFIRMADO", cita.getEstado());

        verify(citaRepository).save(any(Cita.class));

    }

    @Test
    void testMostrarHorarioDisponibleCita() {

        Cita cita = new Cita();
        cita.setFechaCitacion(LocalDate.now());
        cita.setHoraCitacion(LocalTime.of(10, 0));

        List<Cita> citas = List.of(cita);

        when(citaRepository.findAll()).thenReturn(citas);

        List<LocalTime> resultado = service.mostrarHorarioDisponibleHoy();

        assertNotNull(resultado);

        assertFalse(resultado.contains(LocalTime.of(10, 0)));

        assertTrue(resultado.size() > 0);

        verify(citaRepository).findAll();
    }

    @Test
    void testMostrarHorariosPorFecha() {

        String fecha = "2026-04-29";

        Cita cita = new Cita();
        cita.setFechaCitacion(LocalDate.parse(fecha));
        cita.setHoraCitacion(LocalTime.of(10, 0));

        when(citaRepository.findAll())
                .thenReturn(List.of(cita));

        List<LocalTime> resultado = service.mostrarHorariosPorFecha(fecha);

        assertNotNull(resultado);

        assertFalse(resultado.contains(LocalTime.of(10, 0)));

        verify(citaRepository).findAll();

    }

}
