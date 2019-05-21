/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.beans;

import dao.AsistenteDAO;
import delegate.AsistenteDelegate;
import delegate.EventoDelegate;
import dto.AsistenteDTO;
import dto.DatosGrafica;
import dto.EventoDTO;
import entidades.Evento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.jfree.data.general.DefaultPieDataset;
import services.Email;

/* @author Asuncion */
@ManagedBean(name = "AsistenteBean")
@SessionScoped
public class AsistenteBean extends BaseBean {

    private AsistenteDTO asistenteDTO;
    private EventoDTO eventoDTO;
    private EventoDelegate eventoDelegate;
    

    public AsistenteBean() {
    }

    public String nuevo() {
        asistenteDTO = new AsistenteDTO();
        setAccion(ACC_CREAR);
        return "/asistentes/nuevoAsistente.xhtml";
    }

    public String editar() {
        setAccion(ACC_ACTUALIZAR);
        return "/asistentes/capturarAsistente.xhtml";
    }

    public String crear() {
        System.out.println("nadaaaaa2");
        AsistenteDelegate asistenteDelegate = new AsistenteDelegate();
        try {

            System.out.println("prueba:: " + asistenteDTO.getEntidad().getGenero());
            if(asistenteDTO.getEntidad().getGenero()==null){
                asistenteDTO.getEntidad().setGenero("M".charAt(0));
            }
            asistenteDelegate.crearAsistente(asistenteDTO);
            /*
            Email email = new Email();
            
            email.sendEmailText(asistenteDTO.getEntidad().getEmailAsistente(), "Registro exitoso", "Se ha registrado al evento: "+asistenteDTO.getEntidad().getNombreAsistente() + " para el evento " + this.getEventoAsEvento().getNombreEvento());
            */
            return "/asistentes/listadoAsistente.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            error("errorCrearAsistente", "Error al crear articulo");
            return "/asistentes/listadoAsistente.xhtml";
        }
    }

    public String actualizar() {
        System.out.println("nadaaaaa");
        AsistenteDelegate asistenteDelegate = new AsistenteDelegate();
        System.out.println("SEMESTRE: " + this.getSemestre());
        System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().toString());

        try {
            System.out.println(asistenteDTO.getEntidad().getSemestre());
            asistenteDelegate.actualiza(asistenteDTO);
            return "/asistentes/listadoAsistente.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            error("errorCrearAlumno", "Error al crear articulo");
            return "/asistentes/listadoAsistente.xhtml";
        }
    }

    public String borrar() {
        AsistenteDelegate asistenteDelegate = new AsistenteDelegate();
        try {
            asistenteDelegate.elimina(asistenteDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "/articulos/listadoAsistente.xhtml";
    }

    public List getLista() {
        AsistenteDelegate asistenteDelegate = new AsistenteDelegate();
        try {
            System.out.println(asistenteDelegate.listarAsistentes());
            return asistenteDelegate.listarAsistentes();
        } catch (Exception e) {
            e.printStackTrace();
            error("ErrorListaAsistentes", "Error al mostrar articulos");
            return null;
        }
    }

    public List getListaEventos() {
        EventoDelegate eventoDelegate = new EventoDelegate();
        try {

            return eventoDelegate.listarEventos();
        } catch (Exception e) {
            e.printStackTrace();
            error("ErrorListaAsistentes", "Error al mostrar articulos");
            return null;
        }
    }

    public void seleccionaAsistente(ActionEvent event) {
        AsistenteDelegate asistenteDelegate = new AsistenteDelegate();
        String claveArtSel = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("claveArtSel");
        asistenteDTO = new AsistenteDTO();
        asistenteDTO.getEntidad().setIdAsistente(Integer.parseInt(claveArtSel));
        try {
            asistenteDTO = asistenteDelegate.leerAsistente(asistenteDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    

    public String getIdAsistente() {
        System.out.println(asistenteDTO.getEntidad().toString());
        if(asistenteDTO.getEntidad().getIdAsistente()!=null)
        return asistenteDTO.getEntidad().getIdAsistente().toString();
        else{
            return "0";
        }
    }

    public void setIdAsistente(String idAsistente) {
        asistenteDTO.getEntidad().setIdAsistente(Integer.parseInt(idAsistente));
    }

    public String getNombre() {
        if(asistenteDTO.getEntidad().getNombreAsistente()!=null)
        return asistenteDTO.getEntidad().getNombreAsistente();
        else{
            return "";
        }
    }

    public void setNombre(String descripcion) {
        asistenteDTO.getEntidad().setNombreAsistente(descripcion);
    }

    public String getPaterno() {
        if(asistenteDTO.getEntidad().getPaternoAsistente()!=null)
        return asistenteDTO.getEntidad().getPaternoAsistente();
        else{
            return "";
        }
    }

    public void setPaterno(String existencias) {
        asistenteDTO.getEntidad().setPaternoAsistente(existencias);
    }

    public String getMaterno() {
        if(asistenteDTO.getEntidad().getMaternoAsistente()!=null)
        return asistenteDTO.getEntidad().getMaternoAsistente();
        else{
            return "";
        }
    }

    public void setMaterno(String existencias) {
        asistenteDTO.getEntidad().setMaternoAsistente(existencias);
    }

    public String getEmail() {
        if(asistenteDTO.getEntidad().getEmailAsistente()!=null)
        return asistenteDTO.getEntidad().getEmailAsistente();
        else{
            return "";
        }
    }

    public void setEmail(String precio) {
        asistenteDTO.getEntidad().setEmailAsistente(precio);
    }

    public int getSemestre() {
        return asistenteDTO.getEntidad().getSemestre();
    }

    public void setSemestre(int precio) {
        asistenteDTO.getEntidad().setSemestre(precio);
    }

    public String getGenero() {
        if(asistenteDTO.getEntidad().getGenero()!=null)
        return asistenteDTO.getEntidad().getGenero()+"";
        else{
            return "M";
        }
    }

    public void setGenero(String precio) {
        asistenteDTO.getEntidad().setGenero(precio.charAt(0));
    }

    public boolean getActivo() {
        return asistenteDTO.getEntidad().getActivo();
    }

    public void setActivo(boolean precio) {
        asistenteDTO.getEntidad().setActivo(precio);
    }

    public String getObservaciones() {
        if(asistenteDTO.getEntidad().getObservaciones()!=null)
        return asistenteDTO.getEntidad().getObservaciones();
        else{
            return "";
        }
    }

    public void setObservaciones(String precio) {
        asistenteDTO.getEntidad().setObservaciones(precio);
    }

    public Integer getEvento() throws SQLException {
        EventoDelegate eventoDelegate = new EventoDelegate();
//        System.out.println("getting evento");
//        System.out.println(asistenteDTO.getEntidad().toString());
        if (asistenteDTO.getEntidad().getIdEvento()!= null) {
            return asistenteDTO.getEntidad().getIdEvento().getIdEvento();
        } else {
            return eventoDelegate.listarEventos().get(0).getEntidad().getIdEvento();
        }
    }
    
    
    public Evento getEventoAsEvento() throws SQLException {
        EventoDelegate eventoDelegate = new EventoDelegate();
//        System.out.println("getting evento");
//        System.out.println(asistenteDTO.getEntidad().toString());
        if (asistenteDTO.getEntidad().getIdEvento()!= null) {
            return eventoDelegate.leerEvento(new EventoDTO(asistenteDTO.getEntidad().getIdEvento())).getEntidad();
            //return asistenteDTO.getEntidad().getIdEvento();
        } else {
            return eventoDelegate.listarEventos().get(0).getEntidad();
        }
    }

    public void setEvento(Integer existencias) {
        System.out.println("AJUSTANDO EVENTO: " + existencias);
        asistenteDTO.getEntidad().setIdEvento(new Evento(existencias));
    }
    
    public DefaultPieDataset getGraficaAsistente(){
        AsistenteDAO dao=new AsistenteDAO();
        
        Connection cnn = null;
        String user = "root";
        String pwd = "root";
        String url = "jdbc:mysql://localhost:3306/EventosEscom";
        String mySqlDriver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(mySqlDriver);
            cnn = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        DefaultPieDataset dps=new DefaultPieDataset();
        try{
            List datos = dao.datosGrafica(cnn);
            for(int indice=0; indice<datos.size(); indice++){
                DatosGrafica dg=(DatosGrafica) datos.get(indice);
                dps.setValue(dg.getEvento(), dg.getCantidad());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return dps;
    }
}
