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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Categoria generated by hbm2java
 */
@Entity
@Table(name="categoria"
    ,catalog="inventarios"
    , uniqueConstraints = @UniqueConstraint(columnNames="descripcion") 
)
public class Categoria  implements java.io.Serializable {


     private Integer idcategoria;
     private String descripcion;
     private Float precio;
     private Integer existencia;
     private Set<Articulo> articulos = new HashSet<Articulo>(0);

    public Categoria() {
    }

	
    public Categoria(String descripcion) {
        this.descripcion = descripcion;
    }
    public Categoria(String descripcion, Float precio, Integer existencia, Set<Articulo> articulos) {
       this.descripcion = descripcion;
       this.precio = precio;
       this.existencia = existencia;
       this.articulos = articulos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idcategoria", unique=true, nullable=false)
    public Integer getIdcategoria() {
        return this.idcategoria;
    }
    
    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    
    @Column(name="descripcion", unique=true, nullable=false, length=30)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Column(name="precio", precision=12, scale=0)
    public Float getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    
    @Column(name="existencia")
    public Integer getExistencia() {
        return this.existencia;
    }
    
    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="categoria")
    public Set<Articulo> getArticulos() {
        return this.articulos;
    }
    
    public void setArticulos(Set<Articulo> articulos) {
        this.articulos = articulos;
    }




}


