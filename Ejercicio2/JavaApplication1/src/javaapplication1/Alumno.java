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
public class Alumno implements Serializable{
    private int noboleta;
    private String nombre;
    private String materno;
    private String paterno;
    private String domicilio;
    private String email;
    private Carrera carrera;

    public int getNoboleta() {
        return noboleta;
    }

    public void setNoboleta(int noboleta) {
        this.noboleta = noboleta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Alumno(int noboleta, String nombre, String materno, String paterno, String domicilio, String email, Carrera carrera) {
        this.noboleta = noboleta;
        this.nombre = nombre;
        this.materno = materno;
        this.paterno = paterno;
        this.domicilio = domicilio;
        this.email = email;
        this.carrera = carrera;
    }
    

    public Alumno() {
    }

    @Override
    public String toString() {
        return "Alumno{" + "noboleta=" + noboleta + ", nombre=" + nombre + ", materno=" + materno + ", paterno=" + paterno + ", domicilio=" + domicilio + ", email=" + email + ", carrera=" + carrera + '}';
    }
        
}
