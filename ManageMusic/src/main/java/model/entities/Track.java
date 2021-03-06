package model.entities;
// Generated 29/05/2019 10:03:36 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Proxy;

/**
 * Track generated by hbm2java
 */
@Entity
@Proxy(lazy = false)
@Table(name="track"
    ,schema="public"
)
public class Track  implements java.io.Serializable {


     private int trackid;
     private Album album;
     private Genre genre;
     private Mediatype mediatype;
     private String name;
     private String composer;
     private BigDecimal miliseconds;
     private Integer bytes;
     private BigDecimal unitprice;
     private Set<Invoiceline> invoicelines = new HashSet<Invoiceline>(0);

    public Track() {
    }

	
    public Track(int trackid, Album album, Genre genre, Mediatype mediatype) {
        this.trackid = trackid;
        this.album = album;
        this.genre = genre;
        this.mediatype = mediatype;
    }
    public Track(int trackid, Album album, Genre genre, Mediatype mediatype, String name, String composer, BigDecimal miliseconds, Integer bytes, BigDecimal unitprice, Set<Invoiceline> invoicelines) {
       this.trackid = trackid;
       this.album = album;
       this.genre = genre;
       this.mediatype = mediatype;
       this.name = name;
       this.composer = composer;
       this.miliseconds = miliseconds;
       this.bytes = bytes;
       this.unitprice = unitprice;
       this.invoicelines = invoicelines;
    }
   
     @Id 
@GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name="trackid", unique=true, nullable=false)
    public int getTrackid() {
        return this.trackid;
    }
    
    public void setTrackid(int trackid) {
        this.trackid = trackid;
    }

@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="albumid", nullable=false)
    public Album getAlbum() {
        return this.album;
    }
    
    public void setAlbum(Album album) {
        this.album = album;
    }

@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="genreid", nullable=false)
    public Genre getGenre() {
        return this.genre;
    }
    
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="mediatypeid", nullable=false)
    public Mediatype getMediatype() {
        return this.mediatype;
    }
    
    public void setMediatype(Mediatype mediatype) {
        this.mediatype = mediatype;
    }

    
    @Column(name="name")
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="composer")
    public String getComposer() {
        return this.composer;
    }
    
    public void setComposer(String composer) {
        this.composer = composer;
    }

    
    @Column(name="miliseconds", precision=131089, scale=0)
    public BigDecimal getMiliseconds() {
        return this.miliseconds;
    }
    
    public void setMiliseconds(BigDecimal miliseconds) {
        this.miliseconds = miliseconds;
    }

    
    @Column(name="bytes")
    public Integer getBytes() {
        return this.bytes;
    }
    
    public void setBytes(Integer bytes) {
        this.bytes = bytes;
    }

    
    @Column(name="unitprice", precision=131089, scale=0)
    public BigDecimal getUnitprice() {
        return this.unitprice;
    }
    
    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

@OneToMany(fetch=FetchType.EAGER, mappedBy="track")
    public Set<Invoiceline> getInvoicelines() {
        return this.invoicelines;
    }
    
    public void setInvoicelines(Set<Invoiceline> invoicelines) {
        this.invoicelines = invoicelines;
    }




}


