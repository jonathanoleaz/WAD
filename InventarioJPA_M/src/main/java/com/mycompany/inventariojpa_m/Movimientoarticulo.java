/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inventariojpa_m;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jonat
 */
@Entity
@Table(name = "movimientoarticulo")
@NamedQueries({
    @NamedQuery(name = "Movimientoarticulo.findAll", query = "SELECT m FROM Movimientoarticulo m")
    , @NamedQuery(name = "Movimientoarticulo.findByFolio", query = "SELECT m FROM Movimientoarticulo m WHERE m.folio = :folio")
    , @NamedQuery(name = "Movimientoarticulo.findByFecha", query = "SELECT m FROM Movimientoarticulo m WHERE m.fecha = :fecha")
    , @NamedQuery(name = "Movimientoarticulo.findByTipo", query = "SELECT m FROM Movimientoarticulo m WHERE m.tipo = :tipo")
    , @NamedQuery(name = "Movimientoarticulo.findByCantidad", query = "SELECT m FROM Movimientoarticulo m WHERE m.cantidad = :cantidad")})
public class Movimientoarticulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "folio")
    private Integer folio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "fecha")
    private String fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "cantidad")
    private Integer cantidad;
    @JoinColumn(name = "claveart", referencedColumnName = "claveart")
    @ManyToOne(optional = false)
    private Articulo claveart;

    public Movimientoarticulo() {
    }

    public Movimientoarticulo(Integer folio) {
        this.folio = folio;
    }

    public Movimientoarticulo(Integer folio, String fecha, String tipo) {
        this.folio = folio;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Articulo getClaveart() {
        return claveart;
    }

    public void setClaveart(Articulo claveart) {
        this.claveart = claveart;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (folio != null ? folio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimientoarticulo)) {
            return false;
        }
        Movimientoarticulo other = (Movimientoarticulo) object;
        if ((this.folio == null && other.folio != null) || (this.folio != null && !this.folio.equals(other.folio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.inventariojpa_m.Movimientoarticulo[ folio=" + folio + " ]";
    }
    
}
