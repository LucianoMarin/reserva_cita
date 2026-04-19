package com.sumativa1.reserva_cita.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "especialidad")
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_especialidad")
    @Pattern(regexp = "^[a-zA-Z].+$", message = "Solo se acepta en nombreEspecialidad: Letras")
    @NotBlank(message = "Los campos de nombreEspecialidad no pueden estar vacios")
    private String nombreEspecialidad;

    public Especialidad() {
    }

    public Especialidad(String nombreEspecialidad) {

        this.nombreEspecialidad = nombreEspecialidad;

    }

    public Especialidad(Long id, String nombreEspecialidad) {
        this.id = id;
        this.nombreEspecialidad = nombreEspecialidad;

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEspecialidad() {
        return this.nombreEspecialidad;
    }

    public void setNombreEspecialidad(String tipoEspecialidad) {
        this.nombreEspecialidad = tipoEspecialidad;
    }

}
