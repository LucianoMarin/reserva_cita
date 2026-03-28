package com.sumativa1.reserva_cita.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sumativa1.reserva_cita.model.Cita;

@RestController
public class CitaController {

    private List<Cita> citas = new ArrayList<>();

    public CitaController() {

        /*
         * EJEMPLO DE INGRESOS SI SE TUVIERA QUE CREAR LOS OBJETOS
         * 
         * Especialidad especialidad1=new Especialidad(1,"Cirugia");
         * Especialidad especialidad2=new Especialidad(2,"Psicologia");
         * Especialidad especialidad3=new Especialidad(3,"Diabetologo");
         * Especialidad especialidad4=new Especialidad(4,"Gastroenterologo");
         * Especialidad especialidad5=new Especialidad(5,"Psiquiatria");
         * Especialidad especialidad6=new Especialidad(6,"Pediatria");
         * Especialidad especialidad7=new Especialidad(7,"Ginecologia");
         * Especialidad especialidad8=new Especialidad(8,"Neurologia");
         * 
         * 
         * Paciente paciente1=new Paciente(1,"18775116-0","Luciano","Marin");
         * Paciente paciente2=new Paciente(2,"9415449-9","Lujana","Eunice");
         * 
         * 
         * Medico medico1=new
         * Medico(1,"med01374","9234134-1","Ozzy","Osburne","Osburne",especialidad5);
         * 
         * 
         */

        citas.add(new Cita(1, "18775116-0", null, "med01374", LocalTime.of(8, 00, 00), LocalDate.of(2026, 03, 27),
                LocalDate.of(2026, 03, 27)));
        citas.add(
                new Cita(2, "18775116-0", null, "med01374", LocalTime.of(9, 00, 00), LocalDate.of(2026, 03, 28), null));
        citas.add(new Cita(3, "18775116-0", null, "med01374", LocalTime.of(11, 00, 00), LocalDate.of(2026, 03, 28),
                LocalDate.of(2026, 03, 18)));
        citas.add(
                new Cita(4, "18775116-0", null, "med01374", LocalTime.of(12, 00, 00), LocalDate.of(2026, 03, 27), null));
        citas.add(
                new Cita(5, "18775116-0", null, "med01374", LocalTime.of(9, 00, 00), LocalDate.of(2026, 03, 27), null));
        citas.add(
                new Cita(6, "18775116-0", null, "med01374", LocalTime.of(9, 00, 00), LocalDate.of(2025, 03, 30), null));
        citas.add(
                new Cita(7, "18775116-0", null, "med01374", LocalTime.of(9, 00, 00), LocalDate.of(2022, 03, 30), null));
        citas.add(
                new Cita(8, "18775116-0", null, "med01374", LocalTime.of(9, 00, 00), LocalDate.of(2026, 03, 30), null));

        cambiarEstado();

    }

    /* FUNCION PARA LISTAR HORARIOS PRE-DEFINIDOS */
    public List<LocalTime> listadoHorario() {

        /*
         * SE CONSIDERA QUE DIARIAMENTE SE TIENE ESTOS HORARIOS DE DISPONIBILIDAD DE
         * PENSEMOS QUE EL SOLO SE ATENDERA EN LA MAÑANA Y CON UNA DURACION DE 1 HORA (ES FICTICIO)
         */

        List<LocalTime> horarioBase = new ArrayList<>();
        horarioBase.add(LocalTime.of(8, 00, 00));
        horarioBase.add(LocalTime.of(9, 00, 00));
        horarioBase.add(LocalTime.of(10, 00, 00));
        horarioBase.add(LocalTime.of(11, 00, 00));
        horarioBase.add(LocalTime.of(12, 00, 00));

        return horarioBase;
    }


    /* FUNCION PARA CAMBIAR ESTADOS */
    public void cambiarEstado() {

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

        for (Cita c : citas) {

            if ((c.getFechaCitacion() != null && c.getFechaConfirmacion() != null)
                    && ((c.getFechaCitacion().equals(c.getFechaConfirmacion())))) {

                c.setEstado("Confirmado");
            } else if (c.getFechaCitacion() != null && c.getFechaConfirmacion() == null) {

                c.setEstado("Sin confirmar");
            } else {

                c.setEstado("Porfavor Validar en admisión");

            }

        }

    }
/* ENDPOINT MUESTRA TODAS LAS CITAS MEDICAS */
    @GetMapping("/citas")
    public List<Cita> mostrarCitas() {

        return citas;

    }


/* ENDPOINT MUESTRA UNA CITA MEDICA */
    @GetMapping("/citas/{id}")
    public Cita mostrarCita(@PathVariable int id) {

        for (Cita c : citas) {

            if (id == c.getId()) {
                return c;
            }

        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada");
    }


    /* ENDPOINT MUESTRA TODAS LAS HORAS DISPONIBLES DE HOY (SE USA METODO LOCALTIME.NOW())
    POR EJEMPLO PARA LA FECHA DE 27-03-2026, SOLO MOSTRARIA 11:00 QUE NO FUE ASIGNADO
    
    */
    @GetMapping("/citas/horarios_disponibles")
    public List<LocalTime> mostrarHorariosDisponibles() {

        List<LocalTime> horasDisponiblesHoy = new ArrayList<>(listadoHorario());

        for (Cita c : citas) {

            if (LocalDate.now().equals(c.getFechaCitacion())) {

                horasDisponiblesHoy.remove(c.getHoraCitacion());

            }

        }

        return horasDisponiblesHoy;
    }




/* ENDPOINT PARA CONSULTAR POR DISPONIBILIDAD EN UNA FECHA X SE DEBE PASAR POR URL LOS DATOS EN ESTE FORMATO YYYY-MM-DD */

        @GetMapping("/citas/horarios_disponibles/{fecha}")
    public List <LocalTime> mostrarHoraDisponible(@PathVariable String fecha) {

        List<LocalTime> horasDisponibles = new ArrayList<>(listadoHorario());

        LocalDate idfecha=LocalDate.parse(fecha);

        for (Cita c : citas) {


            if (idfecha.equals(c.getFechaCitacion())) {

                horasDisponibles.remove(c.getHoraCitacion());

            }

        }

        return horasDisponibles;
    }



}
