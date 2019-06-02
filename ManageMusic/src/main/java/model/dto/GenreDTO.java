package model.dto;


import model.entities.Genre;
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
public class GenreDTO implements Serializable{
    Genre entidad;
    public GenreDTO(){
        entidad=new Genre();
        entidad.setGenreid(-1);
    }
    public GenreDTO(Genre entidad){
        this.entidad=entidad;
    }
    
    public Genre getEntidad(){
        return entidad;
    }
    
    public void setEntidad(){
        this.entidad=entidad;
    }
    
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("idGenre").append(getEntidad().getGenreid()).append("\n");
        
        return sb.toString();
    }
    
    
    
}
