package entities;
// Generated 7/04/2019 03:22:10 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Articulo generated by hbm2java
 */
@Entity
@Table(name="articulo"
    ,catalog="inventarios"
    , uniqueConstraints = @UniqueConstraint(columnNames="descripcion") 
)
public class Articulo  implements java.io.Serializable {


     private Integer claveart;
     private Categoria categoria;
     private String descripcion;
     private float precio;
     private Integer existencia;
     private Set<Movimientoarticulo> movimientoarticulos = new HashSet<Movimientoarticulo>(0);

    public Articulo() {
    }

	
    public Articulo(String descripcion, float precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }
    public Articulo(Categoria categoria, String descripcion, float precio, Integer existencia, Set<Movimientoarticulo> movimientoarticulos) {
       this.categoria = categoria;
       this.descripcion = descripcion;
       this.precio = precio;
       this.existencia = existencia;
       this.movimientoarticulos = movimientoarticulos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="claveart", unique=true, nullable=false)
    public Integer getClaveart() {
        return this.claveart;
    }
    
    public void setClaveart(Integer claveart) {
        this.claveart = claveart;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idcategoria")
    public Categoria getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    
    @Column(name="descripcion", unique=true, nullable=false, length=30)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Column(name="precio", nullable=false, precision=12, scale=0)
    public float getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    
    @Column(name="existencia")
    public Integer getExistencia() {
        return this.existencia;
    }
    
    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="articulo")
    public Set<Movimientoarticulo> getMovimientoarticulos() {
        return this.movimientoarticulos;
    }
    
    public void setMovimientoarticulos(Set<Movimientoarticulo> movimientoarticulos) {
        this.movimientoarticulos = movimientoarticulos;
    }




}

