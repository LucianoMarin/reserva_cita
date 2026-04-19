package com.sumativa1.reserva_cita.model;

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
@Table(name = "medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(unique = true, name = "codigo_medico")
    private String codigoMedico;

    @Column(unique = true, name = "rut")
    @Pattern(regexp = "^[0-9]{7,8}-[0-9kK]$", message = "Rut no valido, recuerde colocar el - y codigo verificador")
    @NotBlank(message = "El campo rut no puede estar vacio en medico")
    private String rut;

    @Column(name = "nombre")
    @Pattern(regexp = "^[a-zA-Z].+$", message = "Solo se acepta en nombre: Letras")
    @NotBlank(message = "Los campos de nombre no pueden estar vacios")
    private String nombre;

    @Column(name = "apellido_paterno")
    @Pattern(regexp = "^[a-zA-Z].+$", message = "Solo se acepta en apellido_paterno: Letras")
    @NotBlank(message = "Los campos de apellido_paterno no pueden estar vacios")
    private String apellido_paterno;

    @Column(name = "apellido_materno")
    @Pattern(regexp = "^[a-zA-Z].+$", message = "Solo se acepta en apellido_materno: Letras")
    private String apellido_materno;

    @JoinColumn(name = "especialidad_id")
    @ManyToOne
    @NotNull(message = "el campo de especialidad_id no pueden estar vacios")

    private Especialidad especialidad;

    public Medico() {
    }

    public Medico(String codigoMedico, String rut, String nombre, String apellido_paterno,
            String apellido_materno, Especialidad especialidad) {

        this.codigoMedico = codigoMedico;
        this.rut = rut;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.especialidad = especialidad;
    }

    public Medico(Long id, String codigoMedico, String rut, String nombre, String apellido_paterno,
            String apellido_materno, Especialidad especialidad) {
        this.id = id;
        this.codigoMedico = codigoMedico;
        this.rut = rut;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.especialidad = especialidad;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoMedico() {
        return this.codigoMedico;
    }

    public void setCodigoMedico(String codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public String getRut() {
        return this.rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return this.apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return this.apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public Especialidad getEspeciadad() {
        return this.especialidad;
    }

    public void setEspeciadad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

}
