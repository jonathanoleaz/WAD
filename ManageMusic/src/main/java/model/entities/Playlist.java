package model.entities;
// Generated 29/05/2019 10:03:36 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Playlist generated by hbm2java
 */
@Entity
@Table(name="playlist"
    ,schema="public"
)
public class Playlist  implements java.io.Serializable {


     private int playlistid;
     private Mediatype mediatype;

    public Playlist() {
    }

    public Playlist(int playlistid, Mediatype mediatype) {
       this.playlistid = playlistid;
       this.mediatype = mediatype;
    }
   
     @Id 

    
    @Column(name="playlistid", unique=true, nullable=false)
    public int getPlaylistid() {
        return this.playlistid;
    }
    
    public void setPlaylistid(int playlistid) {
        this.playlistid = playlistid;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="name", nullable=false)
    public Mediatype getMediatype() {
        return this.mediatype;
    }
    
    public void setMediatype(Mediatype mediatype) {
        this.mediatype = mediatype;
    }




}


