package com.sumativa1.reserva_cita.model;

public class Medico {

    private int id;
    private String codigoMedico;
    private String rut;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private Especialidad especialidad;


    public Medico(int id, String codigoMedico, String rut, String nombre, String apellido_paterno, String apellido_materno, Especialidad especialidad) {
        this.id = id;
        this.codigoMedico = codigoMedico;
        this.rut = rut;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.especialidad = especialidad;
    }





    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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
