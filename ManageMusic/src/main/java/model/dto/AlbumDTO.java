package model.dto;


import model.entities.Album;
import java.io.Serializable;

public class AlbumDTO implements Serializable{
    Album entidad;
    public AlbumDTO(){
        entidad=new Album();
        entidad.setAlbumid(-1);
    }
    public AlbumDTO(Album entidad){
        this.entidad=entidad;
    }
    
    public Album getEntidad(){
        return entidad;
    }
    
    public void setEntidad(){
        this.entidad=entidad;
    }
    
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("idAlbum").append(getEntidad().getAlbumid()).append("\n");
        
        return sb.toString();
    }
    
    
    
}
