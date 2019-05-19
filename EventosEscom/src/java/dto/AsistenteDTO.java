package dto;


import entidades.Asistente;
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
public class AsistenteDTO implements Serializable{
    Asistente entidad;
    public AsistenteDTO(){
        entidad=new Asistente();
    }
    public AsistenteDTO(Asistente entidad){
        this.entidad=entidad;
    }
    
    public Asistente getEntidad(){
        return entidad;
    }
    
    public void setEntidad(){
        this.entidad=entidad;
    }
    
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("idAsistente").append(getEntidad().getIdAsistente()).append("\n");
        
        return sb.toString();
    }
    
    
    
}
