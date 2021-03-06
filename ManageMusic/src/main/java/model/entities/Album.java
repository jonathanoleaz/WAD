package model.entities;
// Generated 29/05/2019 10:03:36 PM by Hibernate Tools 4.3.1


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
 * Album generated by hbm2java
 */
@Entity
@Proxy(lazy = false)
@Table(name="album"
    ,schema="public"
)
public class Album  implements java.io.Serializable {


     private int albumid;
     private Artist artist;
     private String name;
     private Set<Playlisttrack> playlisttracks = new HashSet<Playlisttrack>(0);
     private Set<Track> tracks = new HashSet<Track>(0);

    public Album() {
    }

	
    public Album(int albumid, Artist artist) {
        this.albumid = albumid;
        this.artist = artist;
    }
    public Album(int albumid, Artist artist, String name, Set<Playlisttrack> playlisttracks, Set<Track> tracks) {
       this.albumid = albumid;
       this.artist = artist;
       this.name = name;
       this.playlisttracks = playlisttracks;
       this.tracks = tracks;
    }
   
     @Id 

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="albumid", unique=true, nullable=false)
    public int getAlbumid() {
        return this.albumid;
    }
    
    public void setAlbumid(int albumid) {
        this.albumid = albumid;
    }

@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="artistid", nullable=false)
    public Artist getArtist() {
        return this.artist;
    }
    
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    
    @Column(name="name")
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

@OneToMany(fetch=FetchType.EAGER, mappedBy="album")
    public Set<Playlisttrack> getPlaylisttracks() {
        return this.playlisttracks;
    }
    
    public void setPlaylisttracks(Set<Playlisttrack> playlisttracks) {
        this.playlisttracks = playlisttracks;
    }

@OneToMany(fetch=FetchType.EAGER, mappedBy="album")
    public Set<Track> getTracks() {
        return this.tracks;
    }
    
    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }




}


