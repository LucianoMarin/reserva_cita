package com.sumativa1.reserva_cita.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "cita")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Los campos de paciente_id no pueden estar vacios")
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @Pattern(regexp = "(CONFIRMADO|SIN CONFIRMAR|CANCELADO|PORFAVOR VALIDAR EN ADMISION)", message = "Solo se aceptan estados: CONFIRMADO, SIN CONFIRMAR, CANCELADO")
    @NotBlank(message = "Los campos de estado no pueden estar vacios")
    @Column(name = "estado")
    private String estado;

    @NotNull(message = "Los campos de paciente_id no pueden estar vacios")
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @NotNull(message = "Los campos de paciente_id no pueden estar vacios")
    @Column(name = "hora_citacion")
    private LocalTime horaCitacion;

    @NotNull(message = "Los campos de paciente_id no pueden estar vacios")
    @Column(name = "fecha_citacion")
    private LocalDate fechaCitacion;

    @Column(name = "fecha_confirmacion")
    private LocalDate fechaConfirmacion;

    public Cita() {
    }

    public Cita(Long id, Paciente paciente, String estado, Medico medico, LocalTime horaCitacion,
            LocalDate fechaCitacion, LocalDate fechaConfirmacion) {
        this.id = id;
        this.paciente = paciente;
        this.estado = estado;
        this.medico = medico;
        this.horaCitacion = horaCitacion;
        this.fechaCitacion = fechaCitacion;
        this.fechaConfirmacion = fechaConfirmacion;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return this.paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getEstado() {
        return this.estado != null ? this.estado.toUpperCase() : null;

    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Medico getMedico() {
        return this.medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
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
