/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.beans;

import java.sql.SQLException;
import model.delegate.ArtistDelegate;

import model.dto.ArtistDTO;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import static vista.beans.BaseBean.ACC_CREAR;


/* @author Asuncion */
@ManagedBean(name = "ArtistaBean")
@SessionScoped
public class ArtistaBean extends BaseBean {

    private ArtistDTO asistenteDTO;

    public ArtistaBean() {
    }

    public String nuevo() {
        asistenteDTO = new ArtistDTO();
        setAccion(ACC_CREAR);
        return "/artista/nuevo.xhtml";
    }

    public String editar() {
        setAccion(ACC_ACTUALIZAR);
        return "/artista/editar.xhtml";
    }

    public String crear() {
        ArtistDelegate asistenteDelegate = new ArtistDelegate();
        try {

            asistenteDelegate.crearArtist(asistenteDTO);

            List todos = asistenteDelegate.listarArtists();
            ArtistDTO asistenteTemp = (ArtistDTO) todos.get(todos.size() - 1);

            return "/artista/lista.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            error("errorCrearArtist", "Error al crear asistente");
            return "/artista/lista.xhtml";
        }
    }

    public String actualizar() {
        ArtistDelegate asistenteDelegate = new ArtistDelegate();

       // System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().toString());
        try {

            asistenteDelegate.actualiza(asistenteDTO);
            return "/artista/lista.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            error("errorCrearAlumno", "Error al crear articulo");
            return "/artista/lista.xhtml";
        }
    }

    public String borrar() {
        ArtistDelegate asistenteDelegate = new ArtistDelegate();
        try {
            asistenteDelegate.elimina(asistenteDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "/articulos/lista.xhtml";
    }

    public List getLista() throws SQLException {
        ArtistDelegate asistenteDelegate = new ArtistDelegate();
        try {

        } catch (Exception e) {
            e.printStackTrace();
            error("ErrorListaArtists", "Error al mostrar articulos");
            //return null;
        }
        return asistenteDelegate.listarArtists();
    }

    public void seleccionaArtist(ActionEvent event) {
        ArtistDelegate asistenteDelegate = new ArtistDelegate();
        String claveArtSel = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("claveArtSel");
        asistenteDTO = new ArtistDTO();
        asistenteDTO.getEntidad().setArtistid(Integer.parseInt(claveArtSel));
        try {
            asistenteDTO = asistenteDelegate.leerArtist(asistenteDTO);
//            System.out.println(asistenteDTO.getEntidad().getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getIdArtist() {
//        System.out.println(asistenteDTO.getEntidad().toString());
        try{
            return asistenteDTO.getEntidad().getArtistid() + "";
        } catch(NullPointerException e) {
            return 0 + "";
        }

    }

    public void setIdArtist(String idArtist) {
        asistenteDTO.getEntidad().setArtistid(Integer.parseInt(idArtist));
    }

    public String getName() {
        try {
            return asistenteDTO.getEntidad().getName();
        } catch(NullPointerException e) {
            return "";
        }
    }

    public void setName(String descripcion) {
        asistenteDTO.getEntidad().setName(descripcion);
    }

}
