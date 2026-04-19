package com.sumativa1.reserva_cita.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumativa1.reserva_cita.controller.CitaNotFoundException;
import com.sumativa1.reserva_cita.model.Cita;
import com.sumativa1.reserva_cita.model.Medico;
import com.sumativa1.reserva_cita.model.Paciente;
import com.sumativa1.reserva_cita.repository.CitaRepository;
import com.sumativa1.reserva_cita.repository.EspecialidadRepository;
import com.sumativa1.reserva_cita.repository.MedicoRepository;
import com.sumativa1.reserva_cita.repository.PacienteRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class CitaServiceImp implements CitaService {

    @Autowired
    public CitaRepository citaRepository;

    @Autowired
    public MedicoRepository medicoRepository;

    @Autowired
    public EspecialidadRepository especialidadRepository;

    @Autowired
    public PacienteRepository pacienteRepository;

    @Override
    public Cita agregarCita(Cita cita) {

        List<LocalTime> horariosValidos = listadoHorario();

        log.info("SE EJECUTO SERVICEIMP agregar cita");

        if (!horariosValidos.contains(cita.getHoraCitacion())) {
            throw new RuntimeException("Horario no válido, recuerde que los horarios son de 08:00 AM - 12:00 PM");
        }

        boolean ocupado = citaRepository.existsByFechaCitacionAndHoraCitacion(
                cita.getFechaCitacion(),
                cita.getHoraCitacion());

        if (ocupado) {
            throw new RuntimeException("Ese horario ya está ocupado");
        }

        Cita guardada = citaRepository.save(cita);

        guardada.setMedico(
                medicoRepository.findById(guardada.getMedico().getId()).orElse(null));

        guardada.setPaciente(
                pacienteRepository.findById(guardada.getPaciente().getId()).orElse(null));

        return guardada;

    }

    @Override
    public void eliminarCita(Long id) {

        log.info("SE EJECUTO SERVICEIMP Eliminar Cita");

        if (!citaRepository.existsById(id)) {

            throw new CitaNotFoundException("la cita (id) que desea eliminar, no existe");

        }

        log.info("Cita con id {} eliminada correctamente", id);

        citaRepository.deleteById(id);

    }

    @Override
    public Cita mostrarCitaId(Long id) {

        log.info("SE EJECUTO SERVICEIMP mostrar cita por id");

        return citaRepository.findById(id).orElseThrow(() -> new CitaNotFoundException(
                "Cita no encontrado con ID: " + id));
    }

    @Override
    public List<Cita> mostrarCitas() {

        log.info("SE EJECUTO SERVICEIMP MOSTRAR CITAS MEDICAS");

        List<Cita> citas = citaRepository.findAll();

        if (citas.isEmpty()) {
            throw new CitaNotFoundException("No existen registros de cita en la BD");
        }

        return citaRepository.findAll();
    }

    @Override
    public Cita modificarCita(Cita cita, Long id) {

        log.info("SE EJECUTO SERVICEIMP modificar cita");

        Cita existente = citaRepository.findById(id)
                .orElseThrow(() -> new CitaNotFoundException("No existe la Cita"));

        if (cita.getMedico() != null && cita.getMedico().getId() != null) {

            Medico medico = medicoRepository.findById(cita.getMedico().getId())
                    .orElseThrow(() -> new CitaNotFoundException("Medico no existe, intente con otro id"));

            existente.setMedico(medico);
        }

        if (cita.getPaciente() != null && cita.getPaciente().getId() != null) {

            Paciente paciente = pacienteRepository.findById(cita.getPaciente().getId())
                    .orElseThrow(() -> new CitaNotFoundException("Paciente no existe, intente con otro id"));

            existente.setPaciente(paciente);
        }

        if (cita.getEstado() != null) {
            existente.setEstado(cita.getEstado());
        }

        if (cita.getHoraCitacion() != null) {
            existente.setHoraCitacion(cita.getHoraCitacion());
        }

        if (cita.getFechaCitacion() != null) {
            existente.setFechaCitacion(cita.getFechaCitacion());
        }

        if (cita.getFechaConfirmacion() != null) {
            existente.setFechaConfirmacion(cita.getFechaConfirmacion());
        }

        return citaRepository.save(existente);

    }

    public List<LocalTime> listadoHorario() {

        log.info("SE EJECUTO SERVICEIMP listado horario");

        /*
         * SE CONSIDERA QUE DIARIAMENTE SE TIENE ESTOS HORARIOS DE DISPONIBILIDAD DE
         * PENSEMOS QUE EL SOLO SE ATENDERA EN LA MAÑANA Y CON UNA DURACION DE 1 HORA
         * (ES FICTICIO)
         */

        List<LocalTime> horarioBase = new ArrayList<>();
        horarioBase.add(LocalTime.of(8, 00, 00));
        horarioBase.add(LocalTime.of(9, 00, 00));
        horarioBase.add(LocalTime.of(10, 00, 00));
        horarioBase.add(LocalTime.of(11, 00, 00));
        horarioBase.add(LocalTime.of(12, 00, 00));

        return horarioBase;
    }

    @Override
    public String actualizarEstadoCitas() {

        log.info("SE EJECUTO SERVICEIMP Actualizar Estados");

        /*
         * LA LOGIGA DEL ESTADO ES EL SIGUIENTE:
         * PARA QUE EL ESTADO SEA CONFIRMADO: LA FECHA DE CITACION DEBE SER IGUAL AL DE
         * CONFIRMACION
         * PARA QUE EL ESTADO SEA SIN CONFIRMAR: LA FECHA CONFIRMACION NO DEBE ESTAR
         * REGISTRADA
         * FINALMENTE EL ESTADO: VALIDAR EN ADMISION, CORRESPONDE A ALGUN ERROR DE LA
         * FECHA DE CONFIRMACION.
         * 
         */

        List<Cita> citas = citaRepository.findAll();

        for (Cita c : citas) {

            if ((c.getFechaCitacion() != null && c.getFechaConfirmacion() != null)
                    && ((c.getFechaCitacion().equals(c.getFechaConfirmacion())))) {

                c.setEstado("CONFIRMADO");
            } else if (c.getFechaCitacion() != null && c.getFechaConfirmacion() == null) {

                c.setEstado("SIN CONFIRMAR");
            } else {

                c.setEstado("PORFAVOR VALIDAR EN ADMISION");

            }

            citaRepository.save(c);

        }

        return "";
    }

    public List<LocalTime> mostrarHorarioDisponibleHoy() {

        log.info("SE EJECUTO SERVICEIMP mostrar horarios disponibles hoy");

        List<LocalTime> horasDisponibles = new ArrayList<>(listadoHorario());
        List<Cita> citas = citaRepository.findAll();

        LocalDate idfecha = LocalDate.now();

        for (Cita c : citas) {

            if (idfecha.equals(c.getFechaCitacion())) {

                horasDisponibles.remove(c.getHoraCitacion());

            }

        }

        return horasDisponibles;
    }

    public List<LocalTime> mostrarHorariosPorFecha(String fecha) {

        log.info("SE EJECUTO SERVICEIMP mostrar horarios por fecha");

        List<LocalTime> horasDisponibles = new ArrayList<>(listadoHorario());
        List<Cita> citas = citaRepository.findAll();

        LocalDate idfecha = LocalDate.parse(fecha);

        for (Cita c : citas) {

            if (idfecha.equals(c.getFechaCitacion())) {

                horasDisponibles.remove(c.getHoraCitacion());

            }

        }

        return horasDisponibles;
    }

}
