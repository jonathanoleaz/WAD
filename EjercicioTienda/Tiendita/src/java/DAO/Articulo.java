/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;

/**
 *
 * @author jonat
 */
public class Articulo implements Serializable{
    private String clavearticulo;
    private String nombreproducto;
    private String descripcionproducto;
    private Double precio;
    private int existencia;
    private int idcategoria;

    public Articulo(String clavearticulo, String nombreproducto, String descripcionproducto, Double precio, int existencia, int idcategoria) {
        this.clavearticulo = clavearticulo;
        this.nombreproducto = nombreproducto;
        this.descripcionproducto = descripcionproducto;
        this.precio = precio;
        this.existencia = existencia;
        this.idcategoria = idcategoria;
    }

    public Articulo() {
    }

    public String getClavearticulo() {
        return clavearticulo;
    }

    public void setClavearticulo(String clavearticulo) {
        this.clavearticulo = clavearticulo;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public String getDescripcionproducto() {
        return descripcionproducto;
    }

    public void setDescripcionproducto(String descripcionproducto) {
        this.descripcionproducto = descripcionproducto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    @Override
    public String toString() {
        return "Producto{" + "clavearticulo=" + clavearticulo + ", nombreproducto=" + nombreproducto + ", descripcionproducto=" + descripcionproducto + ", precio=" + precio + ", existencia=" + existencia + ", idcategoria=" + idcategoria + '}';
    }
    
    
    
}
