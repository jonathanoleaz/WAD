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

    private ArtistDTO artistDTO;

    public ArtistaBean() {
    }

    public String nuevo() {
        artistDTO = new ArtistDTO();
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

            asistenteDelegate.crearArtist(artistDTO);

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

            asistenteDelegate.actualiza(artistDTO);
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
            asistenteDelegate.elimina(artistDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "/artista/lista.xhtml";
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
        artistDTO = new ArtistDTO();
        artistDTO.getEntidad().setArtistid(Integer.parseInt(claveArtSel));
        try {
            artistDTO = asistenteDelegate.leerArtist(artistDTO);
//            System.out.println(asistenteDTO.getEntidad().getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getIdArtist() {
//        System.out.println(asistenteDTO.getEntidad().toString());
        try{
            return artistDTO.getEntidad().getArtistid() + "";
        } catch(NullPointerException e) {
            return 0 + "";
        }

    }

    public void setIdArtist(String idArtist) {
        artistDTO.getEntidad().setArtistid(Integer.parseInt(idArtist));
    }

    public String getName() {
        try {
            return artistDTO.getEntidad().getName();
        } catch(NullPointerException e) {
            return "";
        }
    }

    public void setName(String descripcion) {
        artistDTO.getEntidad().setName(descripcion);
    }

}
