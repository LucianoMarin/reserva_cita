package com.sumativa1.reserva_cita.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {


    private int id;
    private String rutPaciente;
    private String estado;
    private String codigoMedico;
    private LocalTime horaCitacion;
    private LocalDate fechaCitacion;
    private LocalDate fechaConfirmacion;



    public Cita(int id, String rutPaciente, String estado, String codigoMedico,LocalTime horaCitacion, LocalDate fechaCitacion, LocalDate fechaConfirmacion) {
        this.id = id;
        this.rutPaciente=rutPaciente;
        this.estado = estado;
        this.codigoMedico = codigoMedico;
        this.horaCitacion=horaCitacion;
        this.fechaCitacion = fechaCitacion;
        this.fechaConfirmacion=fechaConfirmacion;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRutPaciente() {
        return this.rutPaciente;
    }

    public void setRutPaciente(String rutPaciente) {
        this.rutPaciente = rutPaciente;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigoMedico() {
        return this.codigoMedico;
    }

    public void setCodigoMedico(String codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public LocalTime getHoraCitacion() {
        return this.horaCitacion;
    }

    public void setHoraCitacion(LocalTime horaCitacion) {
        this.horaCitacion = horaCitacion;
    }

    public LocalDate getFechaCitacion() {
        return this.fechaCitacion;
    }

    public void setFechaCitacion(LocalDate fechaCitacion) {
        this.fechaCitacion = fechaCitacion;
    }

    public LocalDate getFechaConfirmacion() {
        return this.fechaConfirmacion;
    }

    public void setFechaConfirmacion(LocalDate fechaConfirmacion) {
        this.fechaConfirmacion = fechaConfirmacion;
    }




}
