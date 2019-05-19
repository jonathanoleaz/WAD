/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.beans;

import delegate.EventoDelegate;
import dto.EventoDTO;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import static vista.beans.BaseBean.ACC_CREAR;

/* @author Asuncion */
@ManagedBean(name = "EventoBean")
@SessionScoped
public class EventoBean extends BaseBean {

    private EventoDTO alumnoDTO;

    public EventoBean() {
    }

    public String nuevo() {
        alumnoDTO = new EventoDTO();
        setAccion(ACC_CREAR);
        return "/eventos/nuevoEvento.xhtml";
    }

    public String editar() {
        setAccion(ACC_ACTUALIZAR);
        return "/eventos/capturarEvento.xhtml";
    }

    public String crear() {
        EventoDelegate asistenteDelegate = new EventoDelegate();
        try {
            
            System.out.println("prueba:: "+ alumnoDTO.getEntidad().toString());
            asistenteDelegate.crearEvento(alumnoDTO);
            return "/eventos/listadoEvento.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            error("errorCrearEvento", "Error al crear articulo");
            return "/eventos/listadoEvento.xhtml";
        }
    }

    public String actualizar() {
        
        EventoDelegate asistenteDelegate = new EventoDelegate();
        
        System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().toString());

        try {
            
            asistenteDelegate.actualiza(alumnoDTO);
            return "/eventos/listadoEvento.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            error("errorCrearAlumno", "Error al crear articulo");
            return "/eventos/listadoEvento.xhtml";
        }
    }

    public String borrar() {
        EventoDelegate asistenteDelegate = new EventoDelegate();
        try {
            asistenteDelegate.elimina(alumnoDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "/articulos/listadoEvento.xhtml";
    }

    public List getLista() {
        EventoDelegate asistenteDelegate = new EventoDelegate();
        try {
            System.out.println(asistenteDelegate.listarEventos());
            return asistenteDelegate.listarEventos();
        } catch (Exception e) {
            e.printStackTrace();
            error("ErrorListaEventos", "Error al mostrar articulos");
            return null;
        }
    }

    public void seleccionaEvento(ActionEvent event) {
        EventoDelegate asistenteDelegate = new EventoDelegate();
        String claveArtSel = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("claveArtSel");
        alumnoDTO = new EventoDTO();
        alumnoDTO.getEntidad().setIdEvento(Integer.parseInt(claveArtSel));
        try {
            alumnoDTO = asistenteDelegate.leerEvento(alumnoDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getIdEvento() {
        return alumnoDTO.getEntidad().getIdEvento().toString();
    }

    public void setIdEvento(String idEvento) {
        alumnoDTO.getEntidad().setIdEvento(Integer.parseInt(idEvento));
    }

    public String getNombreEvento() {
        return alumnoDTO.getEntidad().getNombreEvento();
    }

    public void setNombreEvento(String descripcion) {
        alumnoDTO.getEntidad().setNombreEvento(descripcion);
    }

    public Date getInicio() {
        return alumnoDTO.getEntidad().getInicio();
    }

    public void setInicio(Date existencias) {
        alumnoDTO.getEntidad().setInicio(existencias);
    }
    
    
    public Date getFin() {
        return alumnoDTO.getEntidad().getInicio();
    }

    public void setFin(Date existencias) {
        alumnoDTO.getEntidad().setFin(existencias);
    }

    public String getObservaciones() {
        return alumnoDTO.getEntidad().getObservaciones();
    }

    public void setObservaciones(String existencias) {
        alumnoDTO.getEntidad().setObservaciones(existencias);
    }
    
    

}
