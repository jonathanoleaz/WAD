package objectAndDao;

import java.io.Serializable;

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

    public int getIdCarrera() {
        return idcarrera;
    }

    public void setIdCarrera(int idcarrera) {
        this.idcarrera = idcarrera;
    }

    public String getNombreCarrera() {
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
        return "Carrera{" + "idcarrera=" + idcarrera + ", nombrecarrera=" + nombrecarrera + ", descripcion=" + descripcion + ", duracion=" + duracion + '}';
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
