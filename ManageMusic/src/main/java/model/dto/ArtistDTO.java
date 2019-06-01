package model.dto;


import model.entities.Artist;
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jonat
 */
public class ArtistDTO implements Serializable{
    Artist entidad;
    public ArtistDTO(){
        entidad=new Artist();
        entidad.setArtistid(-1);
    }
    public ArtistDTO(Artist entidad){
        this.entidad=entidad;
    }
    
    public Artist getEntidad(){
        return entidad;
    }
    
    public void setEntidad(){
        this.entidad=entidad;
    }
    
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("idArtist").append(getEntidad().getArtistid()).append("\n");
        
        return sb.toString();
    }
    
    
    
}
