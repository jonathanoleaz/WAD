package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "evento")
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e")})
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEvento")
    private Integer idEvento;
    @Basic(optional = false)
    @Column(name = "nombreEvento")
    private String nombreEvento;
    @Basic(optional = false)
    @Column(name = "inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicio;
    @Basic(optional = false)
    @Column(name = "fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fin;
    @Lob
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEvento")
    private Collection<Asistente> asistenteCollection;

    public Evento() {
    }

    public Evento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Evento(Integer idEvento, String nombreEvento, Date inicio, Date fin) {
        this.idEvento = idEvento;
        this.nombreEvento = nombreEvento;
        this.inicio = inicio;
        this.fin = fin;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Collection<Asistente> getAsistenteCollection() {
        return asistenteCollection;
    }

    public void setAsistenteCollection(Collection<Asistente> asistenteCollection) {
        this.asistenteCollection = asistenteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvento != null ? idEvento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.idEvento == null && other.idEvento != null) || (this.idEvento != null && !this.idEvento.equals(other.idEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreEvento;
        //return "dao.Evento[ idEvento=" + idEvento + " ]";
    }

}
