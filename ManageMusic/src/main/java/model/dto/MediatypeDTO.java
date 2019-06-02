package model.dto;


import model.entities.Mediatype;
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
public class MediatypeDTO implements Serializable{
    Mediatype entidad;
    public MediatypeDTO(){
        entidad=new Mediatype();
        entidad.setMediatypeid(-1);
    }
    public MediatypeDTO(Mediatype entidad){
        this.entidad=entidad;
    }
    
    public Mediatype getEntidad(){
        return entidad;
    }
    
    public void setEntidad(){
        this.entidad=entidad;
    }
    
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("idMediatype").append(getEntidad().getMediatypeid()).append("\n");
        
        return sb.toString();
    }
    
    
    
}
