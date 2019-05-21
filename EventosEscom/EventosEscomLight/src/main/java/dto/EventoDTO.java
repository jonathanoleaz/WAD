package dto;

import entidades.Evento;
import java.io.Serializable;

public class EventoDTO implements Serializable{
    Evento entidad;
    public EventoDTO(){
        entidad=new Evento();
    }
    public EventoDTO(Evento entidad){
        this.entidad=entidad;
    }
    
    public Evento getEntidad(){
        return entidad;
    }
    
    public void setEntidad(){
        this.entidad=entidad;
    }
    
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("idEvento").append(getEntidad().getIdEvento()).append("\n");
        
        return sb.toString();
    }
    
}
