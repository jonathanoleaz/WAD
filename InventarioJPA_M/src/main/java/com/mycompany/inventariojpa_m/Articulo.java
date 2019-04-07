/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inventariojpa_m;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jonat
 */
@Entity
@Table(name = "articulo")
@NamedQueries({
    @NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a")
    , @NamedQuery(name = "Articulo.findByClaveart", query = "SELECT a FROM Articulo a WHERE a.claveart = :claveart")
    , @NamedQuery(name = "Articulo.findByDescripcion", query = "SELECT a FROM Articulo a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "Articulo.findByPrecio", query = "SELECT a FROM Articulo a WHERE a.precio = :precio")
    , @NamedQuery(name = "Articulo.findByExistencia", query = "SELECT a FROM Articulo a WHERE a.existencia = :existencia")})
public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "claveart")
    private Integer claveart;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private float precio;
    @Column(name = "existencia")
    private Integer existencia;
    @JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria")
    @ManyToOne
    private Categoria idcategoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "claveart")
    private Collection<Movimientoarticulo> movimientoarticuloCollection;

    public Articulo() {
    }

    public Articulo(Integer claveart) {
        this.claveart = claveart;
    }

    public Articulo(Integer claveart, String descripcion, float precio) {
        this.claveart = claveart;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Integer getClaveart() {
        return claveart;
    }

    public void setClaveart(Integer claveart) {
        this.claveart = claveart;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }

    public Categoria getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Categoria idcategoria) {
        this.idcategoria = idcategoria;
    }

    public Collection<Movimientoarticulo> getMovimientoarticuloCollection() {
        return movimientoarticuloCollection;
    }

    public void setMovimientoarticuloCollection(Collection<Movimientoarticulo> movimientoarticuloCollection) {
        this.movimientoarticuloCollection = movimientoarticuloCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claveart != null ? claveart.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if ((this.claveart == null && other.claveart != null) || (this.claveart != null && !this.claveart.equals(other.claveart))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.inventariojpa_m.Articulo[ claveart=" + claveart + " ]";
    }
    
}
