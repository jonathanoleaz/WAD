/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jonat
 */
@Entity
@Table(name = "asistente")
@NamedQueries({
    @NamedQuery(name = "Asistente.findAll", query = "SELECT a FROM Asistente a")})
public class Asistente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAsistente")
    private Integer idAsistente;
    @Basic(optional = false)
    @Column(name = "nombreAsistente")
    private String nombreAsistente;
    @Basic(optional = false)
    @Column(name = "paternoAsistente")
    private String paternoAsistente;
    @Basic(optional = false)
    @Column(name = "maternoAsistente")
    private String maternoAsistente;
    @Basic(optional = false)
    @Column(name = "emailAsistente")
    private String emailAsistente;
    @Basic(optional = false)
    @Column(name = "semestre")
    private int semestre;
    @Basic(optional = false)
    @Column(name = "genero")
    private Character genero;
    @Basic(optional = false)
    @Column(name = "activo")
    private boolean activo;
    @Lob
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "idEvento", referencedColumnName = "idEvento")
    @ManyToOne(optional = false)
    private Evento idEvento;

    public Asistente() {
    }

    public Asistente(Integer idAsistente) {
        this.idAsistente = idAsistente;
    }

    public Asistente(Integer idAsistente, String nombreAsistente, String paternoAsistente, String maternoAsistente, String emailAsistente, int semestre, Character genero, boolean activo) {
        this.idAsistente = idAsistente;
        this.nombreAsistente = nombreAsistente;
        this.paternoAsistente = paternoAsistente;
        this.maternoAsistente = maternoAsistente;
        this.emailAsistente = emailAsistente;
        this.semestre = semestre;
        this.genero = genero;
        this.activo = activo;
    }

    public Integer getIdAsistente() {
        return idAsistente;
    }

    public void setIdAsistente(Integer idAsistente) {
        this.idAsistente = idAsistente;
    }

    public String getNombreAsistente() {
        return nombreAsistente;
    }

    public void setNombreAsistente(String nombreAsistente) {
        this.nombreAsistente = nombreAsistente;
    }

    public String getPaternoAsistente() {
        return paternoAsistente;
    }

    public void setPaternoAsistente(String paternoAsistente) {
        this.paternoAsistente = paternoAsistente;
    }

    public String getMaternoAsistente() {
        return maternoAsistente;
    }

    public void setMaternoAsistente(String maternoAsistente) {
        this.maternoAsistente = maternoAsistente;
    }

    public String getEmailAsistente() {
        return emailAsistente;
    }

    public void setEmailAsistente(String emailAsistente) {
        this.emailAsistente = emailAsistente;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Evento getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Evento idEvento) {
        this.idEvento = idEvento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsistente != null ? idAsistente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistente)) {
            return false;
        }
        Asistente other = (Asistente) object;
        if ((this.idAsistente == null && other.idAsistente != null) || (this.idAsistente != null && !this.idAsistente.equals(other.idAsistente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.Asistente[ idAsistente= " + idAsistente + " ]";
    }
    
    
    
}
