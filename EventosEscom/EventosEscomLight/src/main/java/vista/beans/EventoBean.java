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

            System.out.println("prueba:: " + alumnoDTO.getEntidad().toString());
            asistenteDelegate.crearEvento(alumnoDTO);
            return "/eventos/listadoEventos.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            error("errorCrearEvento", "Error al crear articulo");
            return "/eventos/listadoEventos.xhtml";
        }
    }

    public String actualizar() {

        EventoDelegate asistenteDelegate = new EventoDelegate();

        System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().toString());

        try {

            asistenteDelegate.actualiza(alumnoDTO);
            return "/eventos/listadoEventos.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            error("errorCrearAlumno", "Error al crear articulo");
            return "/eventos/listadoEventos.xhtml";
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
        if (alumnoDTO.getEntidad().getIdEvento() != null) {
            return alumnoDTO.getEntidad().getIdEvento().toString();
        } else {
            return "0";
        }
    }

    public void setIdEvento(String idEvento) {
        alumnoDTO.getEntidad().setIdEvento(Integer.parseInt(idEvento));
    }

    public String getNombreEvento() {
        if (alumnoDTO.getEntidad().getNombreEvento() != null) {
            return alumnoDTO.getEntidad().getNombreEvento();
        } else {
            return "";
        }
    }

    public void setNombreEvento(String descripcion) {
        alumnoDTO.getEntidad().setNombreEvento(descripcion);
    }

    public Date getInicio() {
        //System.out.println("FECCHA de bd: "+alumnoDTO.getEntidad().getInicio().toString());
        if (alumnoDTO.getEntidad().getInicio() != null) {
            return alumnoDTO.getEntidad().getInicio();
        } else {
            return new Date();
        }
    }

    public void setInicio(Date existencias) {
        System.out.println("INICIO: " + existencias.toString());
        alumnoDTO.getEntidad().setInicio(existencias);
    }

    public Date getFin() {
        if (alumnoDTO.getEntidad().getFin() != null) {
            return alumnoDTO.getEntidad().getFin();
        } else {
            return new Date();
        }
    }

    public void setFin(Date existencias) {
        alumnoDTO.getEntidad().setFin(existencias);
    }

    public String getObservaciones() {
        if (alumnoDTO.getEntidad().getObservaciones() != null) {
            return alumnoDTO.getEntidad().getObservaciones();
        } else {
            return "";
        }
    }

    public void setObservaciones(String existencias) {
        alumnoDTO.getEntidad().setObservaciones(existencias);
    }

}
