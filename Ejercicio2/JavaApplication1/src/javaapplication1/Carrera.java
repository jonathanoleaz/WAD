/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.Serializable;

/**
 *
 * @author jonat
 */
public class Carrera implements Serializable{
    private int idcarrera;
    private String nombrecarrera;
    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    private int duracion;

    public int getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(int idcarrera) {
        this.idcarrera = idcarrera;
    }

    public String getNombrecarrera() {
        return nombrecarrera;
    }

    public void setNombrecarrera(String nombrecarrera) {
        this.nombrecarrera = nombrecarrera;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Carrera{" + "idcarrera=" + idcarrera + ", nombrecarrera=" + nombrecarrera + ", duracion=" + duracion + '}';
    }

    public Carrera(int idcarrera, String nombrecarrera, String descripcion, int duracion) {
        this.idcarrera = idcarrera;
        this.nombrecarrera = nombrecarrera;
        this.duracion = duracion;
        this.descripcion=descripcion;
    }

    public Carrera() {
    }
    
    
    
    
    
}
