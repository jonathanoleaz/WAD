package model.dto;


import model.entities.Track;
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
public class TrackDTO implements Serializable{
    Track entidad;
    public TrackDTO(){
        entidad=new Track();
        entidad.setTrackid(-1);
    }
    public TrackDTO(Track entidad){
        this.entidad=entidad;
    }
    
    public Track getEntidad(){
        return entidad;
    }
    
    public void setEntidad(){
        this.entidad=entidad;
    }
    
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("idTrack").append(getEntidad().getTrackid()).append("\n");
        
        return sb.toString();
    }
    
    
    
}
