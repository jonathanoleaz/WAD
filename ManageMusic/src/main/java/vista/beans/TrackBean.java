/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.beans;

import java.math.BigDecimal;
import java.sql.SQLException;
import model.delegate.TrackDelegate;

import model.dto.TrackDTO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.delegate.AlbumDelegate;
import model.delegate.ArtistDelegate;
import model.dto.AlbumDTO;
import model.dto.ArtistDTO;
import model.entities.Album;
import model.entities.Track;
import model.entities.Artist;
import static vista.beans.BaseBean.ACC_CREAR;


/* @author Asuncion */
@ManagedBean(name = "TrackBean")
@SessionScoped
public class TrackBean extends BaseBean {

    private TrackDTO albumDTO;

    public TrackBean() {
    }

    public String nuevo() {
        albumDTO = new TrackDTO();
        setAccion(ACC_CREAR);
        return "/album/nuevo.xhtml";
    }

    public String editar() {
        setAccion(ACC_ACTUALIZAR);
        return "/album/editar.xhtml";
    }

    public String crear() {
        TrackDelegate asistenteDelegate = new TrackDelegate();
        try {

            asistenteDelegate.crearTrack(albumDTO);

            List todos = asistenteDelegate.listarTracks();
            TrackDTO asistenteTemp = (TrackDTO) todos.get(todos.size() - 1);

            return "/album/lista.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            error("errorCrearTrack", "Error al crear asistente");
            return "/album/lista.xhtml";
        }
    }

    public String actualizar() {
        TrackDelegate asistenteDelegate = new TrackDelegate();

       // System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().toString());
        try {
            System.out.println("updating ");
            asistenteDelegate.actualiza(albumDTO);
            return "/album/lista.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            error("errorCrearAlumno", "Error al crear articulo");
            return "/album/lista.xhtml";
        }
    }

    public String borrar() {
        TrackDelegate asistenteDelegate = new TrackDelegate();
        try {
            asistenteDelegate.elimina(albumDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "/album/lista.xhtml";
    }

    public List getLista() throws SQLException {
        TrackDelegate asistenteDelegate = new TrackDelegate();
        try {

        } catch (Exception e) {
            e.printStackTrace();
            error("ErrorListaTracks", "Error al mostrar articulos");
            //return null;
        }
        return asistenteDelegate.listarTracks();
    }
    
    
    public List<ArtistDTO> getListaAlbums() {
        AlbumDelegate eventoDelegate = new AlbumDelegate();
        try {

            return eventoDelegate.listarAlbums();
        } catch (Exception e) {
            e.printStackTrace();
            error("ErrorListaAsistentes", "Error al mostrar articulos");
            return null;
        }
    }

    public void seleccionaTrack(ActionEvent event) {
        TrackDelegate asistenteDelegate = new TrackDelegate();
        String claveArtSel = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("claveArtSel");
        albumDTO = new TrackDTO();
        albumDTO.getEntidad().setTrackid(Integer.parseInt(claveArtSel));
        try {
            albumDTO = asistenteDelegate.leerTrack(albumDTO);
//            System.out.println(asistenteDTO.getEntidad().getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getIdTrack() {
//        System.out.println(asistenteDTO.getEntidad().toString());
        try{
            return albumDTO.getEntidad().getTrackid() + "";
        } catch(NullPointerException e) {
            return 0 + "";
        }

    }

    public void setIdTrack(String idTrack) {
        albumDTO.getEntidad().setTrackid(Integer.parseInt(idTrack));
    }
    
    
    public Integer getAlbum() {
//        System.out.println(asistenteDTO.getEntidad().toString());
        try{
            System.out.println("Dando artista del album: "+albumDTO.getEntidad().getAlbum().getName());
            return albumDTO.getEntidad().getAlbum().getAlbumid();
        } catch(NullPointerException e) {
            return 0;
        }

    }

    public void setAlbum(Integer idTrack) {
        try {
            System.out.println("Ajustando artista del album: ");
            AlbumDelegate artdel=new AlbumDelegate();
            Album art=artdel.leerAlbum(new AlbumDTO(new Album(idTrack, null))).getEntidad();
            albumDTO.getEntidad().setAlbum(art);
        } catch (SQLException ex) {
            Logger.getLogger(TrackBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getName() {
        try {
            return albumDTO.getEntidad().getName();
        } catch(NullPointerException e) {
            return "";
        }
    }

    public void setName(String descripcion) {
        albumDTO.getEntidad().setName(descripcion);
    }
    
    public String getComposer() {
        try {
            return albumDTO.getEntidad().getComposer();
        } catch(NullPointerException e) {
            return "";
        }
    }

    public void setComposer(String descripcion) {
        albumDTO.getEntidad().setComposer(descripcion);
    }
    
    public BigDecimal getMiliseconds() {
        try {
            return albumDTO.getEntidad().getMiliseconds();
        } catch(NullPointerException e) {
            return BigDecimal.ZERO;
        }
    }

    public void setMiliseconds(BigDecimal descripcion) {
        albumDTO.getEntidad().setMiliseconds(descripcion);
    }

}
