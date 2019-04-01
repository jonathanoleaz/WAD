/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendajpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jonat
 */
@Entity
@Table(name = "articulo")
@NamedQueries({
    @NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a")
    , @NamedQuery(name = "Articulo.findByClavearticulo", query = "SELECT a FROM Articulo a WHERE a.clavearticulo = :clavearticulo")
    , @NamedQuery(name = "Articulo.findByNombreproducto", query = "SELECT a FROM Articulo a WHERE a.nombreproducto = :nombreproducto")
    , @NamedQuery(name = "Articulo.findByDescripcionproducto", query = "SELECT a FROM Articulo a WHERE a.descripcionproducto = :descripcionproducto")
    , @NamedQuery(name = "Articulo.findByPrecio", query = "SELECT a FROM Articulo a WHERE a.precio = :precio")
    , @NamedQuery(name = "Articulo.findByExistencia", query = "SELECT a FROM Articulo a WHERE a.existencia = :existencia")})
public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clavearticulo")
    private String clavearticulo;
    @Basic(optional = false)
    @Column(name = "nombreproducto")
    private String nombreproducto;
    @Basic(optional = false)
    @Column(name = "descripcionproducto")
    private String descripcionproducto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @Column(name = "existencia")
    private Integer existencia;
    @JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria")
    @ManyToOne(optional = false)
    private Categoria idcategoria;

    public Articulo() {
    }

    public Articulo(String clavearticulo) {
        this.clavearticulo = clavearticulo;
    }

    public Articulo(String clavearticulo, String nombreproducto, String descripcionproducto) {
        this.clavearticulo = clavearticulo;
        this.nombreproducto = nombreproducto;
        this.descripcionproducto = descripcionproducto;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clavearticulo != null ? clavearticulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if ((this.clavearticulo == null && other.clavearticulo != null) || (this.clavearticulo != null && !this.clavearticulo.equals(other.clavearticulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tiendajpa.Articulo[ clavearticulo=" + clavearticulo + " ]";
    }
    
}
