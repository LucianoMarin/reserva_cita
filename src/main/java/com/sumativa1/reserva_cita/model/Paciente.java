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
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "rut")
    @Pattern(regexp = "^[0-9]{7,8}-[0-9kK]$", message = "Rut no valido, recuerde colocar el - y codigo verificador")
    @NotBlank(message = "El campo rut no puede estar vacio en paciente")

    private String rut;

    @Column(name = "nombre")
    @Pattern(regexp = "^[a-zA-Z].+$", message = "Solo se acepta en nombre: Letras")
    @NotBlank(message = "Los campos de nombre no pueden estar vacios")
    private String nombre;

    @Column(name = "apellido")
    @Pattern(regexp = "^[a-zA-Z].+$", message = "Solo se acepta en apellido: Letras")
    @NotBlank(message = "Los campos de apellido no pueden estar vacios")
    private String apellido;

    public Paciente() {
    }

   public Paciente(String rut, String nombre, String apellido) {

        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;

    }


    public Paciente(Long id, String rut, String nombre, String apellido) {

        this.id = id;
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

}
