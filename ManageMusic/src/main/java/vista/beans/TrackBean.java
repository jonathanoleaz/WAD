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
import model.delegate.GenreDelegate;
import model.delegate.MediatypeDelegate;
import model.dto.AlbumDTO;
import model.dto.ArtistDTO;
import model.dto.GenreDTO;
import model.dto.MediatypeDTO;
import model.entities.Album;
import model.entities.Track;
import model.entities.Artist;
import model.entities.Genre;
import model.entities.Mediatype;
import static vista.beans.BaseBean.ACC_CREAR;


/* @author Asuncion */
@ManagedBean(name = "TrackBean")
@SessionScoped
public class TrackBean extends BaseBean {

    private TrackDTO trackDTO;

    public TrackBean() {
    }

    public String nuevo() {
        trackDTO = new TrackDTO();
        setAccion(ACC_CREAR);
        return "/track/nuevo.xhtml";
    }

    public String editar() {
        setAccion(ACC_ACTUALIZAR);
        return "/track/editar.xhtml";
    }

    public String crear() {
        TrackDelegate asistenteDelegate = new TrackDelegate();
        try {

            asistenteDelegate.crearTrack(trackDTO);

            List todos = asistenteDelegate.listarTracks();
            TrackDTO asistenteTemp = (TrackDTO) todos.get(todos.size() - 1);

            return "/track/lista.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            error("errorCrearTrack", "Error al crear asistente");
            return "/track/lista.xhtml";
        }
    }

    public String actualizar() {
        TrackDelegate asistenteDelegate = new TrackDelegate();

       // System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().toString());
        try {
            System.out.println("updating ");
            asistenteDelegate.actualiza(trackDTO);
            return "/track/lista.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            error("errorCrearAlumno", "Error al crear articulo");
            return "/track/lista.xhtml";
        }
    }

    public String borrar() {
        TrackDelegate asistenteDelegate = new TrackDelegate();
        try {
            asistenteDelegate.elimina(trackDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "/track/lista.xhtml";
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
    
    public List<MediatypeDTO> getListaMediaTypes() {
        MediatypeDelegate eventoDelegate = new MediatypeDelegate();
        try {

            return eventoDelegate.listarMediatypes();
        } catch (Exception e) {
            e.printStackTrace();
            error("ErrorListaAsistentes", "Error al mostrar articulos");
            return null;
        }
    }
    
    public List<GenreDTO> getListaGenre() {
        GenreDelegate genreDelegate = new GenreDelegate();
        try {
            
            return genreDelegate.listarGenres();
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
        trackDTO = new TrackDTO();
        trackDTO.getEntidad().setTrackid(Integer.parseInt(claveArtSel));
        try {
            trackDTO = asistenteDelegate.leerTrack(trackDTO);
//            System.out.println(asistenteDTO.getEntidad().getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getIdTrack() {
//        System.out.println(asistenteDTO.getEntidad().toString());
        try{
            return trackDTO.getEntidad().getTrackid() + "";
        } catch(NullPointerException e) {
            return 0 + "";
        }

    }

    public void setIdTrack(String idTrack) {
        trackDTO.getEntidad().setTrackid(Integer.parseInt(idTrack));
    }
    
    
    public Integer getAlbum() {
//        System.out.println(asistenteDTO.getEntidad().toString());
        try{
            System.out.println("Dando artista del track: "+trackDTO.getEntidad().getAlbum().getName());
            return trackDTO.getEntidad().getAlbum().getAlbumid();
        } catch(NullPointerException e) {
            return 0;
        }
    }

    public void setAlbum(Integer idTrack) {
        try {
            System.out.println("Ajustando artista del track: ");
            AlbumDelegate artdel=new AlbumDelegate();
            Album art=artdel.leerAlbum(new AlbumDTO(new Album(idTrack, null))).getEntidad();
            trackDTO.getEntidad().setAlbum(art);
        } catch (SQLException ex) {
            Logger.getLogger(TrackBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Integer getMediatype() {
//        System.out.println(asistenteDTO.getEntidad().toString());
        try{
            System.out.println("Dando artista del track: "+trackDTO.getEntidad().getMediatype().getName());
            return trackDTO.getEntidad().getAlbum().getAlbumid();
        } catch(NullPointerException e) {
            return 0;
        }
    }

    public void setMediatype(Integer idTrack) {
        try {
            System.out.println("Ajustando artista del track: ");
            MediatypeDelegate artdel=new MediatypeDelegate();
            Mediatype art=artdel.leerMediatype(new MediatypeDTO(new Mediatype(idTrack))).getEntidad();
            trackDTO.getEntidad().setMediatype(art);
        } catch (SQLException ex) {
            Logger.getLogger(TrackBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Integer getGenre() {
//        System.out.println(asistenteDTO.getEntidad().toString());
        try{
            System.out.println("Dando artista del track: "+trackDTO.getEntidad().getMediatype().getName());
            return trackDTO.getEntidad().getGenre().getGenreid();
        } catch(NullPointerException e) {
            return 0;
        }
    }

    public void setGenre(Integer idTrack) {
        try {
            System.out.println("Ajustando artista del track: ");
            GenreDelegate artdel=new GenreDelegate();
            Genre art=artdel.leerGenre(new GenreDTO(new Genre(idTrack))).getEntidad();
            trackDTO.getEntidad().setGenre(art);
        } catch (SQLException ex) {
            Logger.getLogger(TrackBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getName() {
        try {
            return trackDTO.getEntidad().getName();
        } catch(NullPointerException e) {
            return "";
        }
    }

    public void setName(String descripcion) {
        trackDTO.getEntidad().setName(descripcion);
    }
    
    public String getComposer() {
        try {
            return trackDTO.getEntidad().getComposer();
        } catch(NullPointerException e) {
            return "";
        }
    }

    public void setComposer(String descripcion) {
        trackDTO.getEntidad().setComposer(descripcion);
    }
    
    public BigDecimal getMiliseconds() {
        try {
            return trackDTO.getEntidad().getMiliseconds();
        } catch(NullPointerException e) {
            return BigDecimal.ZERO;
        }
    }

    public void setMiliseconds(BigDecimal descripcion) {
        trackDTO.getEntidad().setMiliseconds(descripcion);
    }
    
    
    public Integer getBytes() {
        try {
            return trackDTO.getEntidad().getBytes();
        } catch(NullPointerException e) {
            return 0;
        }
    }

    public void setBytes(Integer b) {
        trackDTO.getEntidad().setBytes(b);
    }
    
    
    public BigDecimal getUnitprice() {
        try {
            return trackDTO.getEntidad().getUnitprice();
        } catch(NullPointerException e) {
            return BigDecimal.ZERO;
        }
    }

    public void setUnitprice(BigDecimal b) {
        trackDTO.getEntidad().setUnitprice(b);
    }

}
