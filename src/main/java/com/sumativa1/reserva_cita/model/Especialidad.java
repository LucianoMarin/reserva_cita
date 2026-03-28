package com.sumativa1.reserva_cita.model;

public class Especialidad {
    

    private int id;
    private String nombreEspecialidad;





    public Especialidad(int id, String nombreEspecialidad) {
        this.id = id;
        this.nombreEspecialidad = nombreEspecialidad;
      
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNombreEspecialidad() {
        return this.nombreEspecialidad;
    }

    public void setNombreEspecialidad(String tipoEspecialidad) {
        this.nombreEspecialidad = tipoEspecialidad;
    }





}
