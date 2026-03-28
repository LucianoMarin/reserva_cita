package com.sumativa1.reserva_cita.model;

public class Paciente {

    private int id;
    private String rut;
    private String nombre;
    private String apellido;


    public Paciente(int id, String rut,String nombre,String apellido){

        this.id=id;
        this.rut=rut;
        this.nombre=nombre;
        this.apellido=apellido;

    }
    

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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
