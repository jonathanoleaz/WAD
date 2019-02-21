package DAO;

import java.io.Serializable;

public class Categoria implements Serializable{
    private int idcategoria;
    private String nombreCategoria;
    private String descripcionCategoria;

    public Categoria(int idcategoria, String nombreCategoria, String descripcionCategoria) {
        this.idcategoria = idcategoria;
        this.nombreCategoria = nombreCategoria;
        this.descripcionCategoria = descripcionCategoria;
    }

    public Categoria() {
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    @Override
    public String toString() {
        return "Categoria{" + "idcategoria=" + idcategoria + ", nombreCategoria=" + nombreCategoria + ", descripcionCategoria=" + descripcionCategoria + '}';
    }        
        
}
