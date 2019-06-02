/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.beans;

import java.sql.SQLException;
import model.delegate.AlbumDelegate;

import model.dto.AlbumDTO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import model.delegate.ArtistDelegate;
import model.dto.ArtistDTO;
import model.entities.Album;
import model.entities.Artist;
import static vista.beans.BaseBean.ACC_CREAR;


/* @author Asuncion */
@ManagedBean(name = "AlbumBean")
@SessionScoped
public class AlbumBean extends BaseBean {

    private AlbumDTO albumDTO;

    public AlbumBean() {
    }

    public String nuevo() {
        albumDTO = new AlbumDTO();
        setAccion(ACC_CREAR);
        return "/album/nuevo.xhtml";
    }

    public String editar() {
        setAccion(ACC_ACTUALIZAR);
        return "/album/editar.xhtml";
    }

    public String crear() {
        AlbumDelegate asistenteDelegate = new AlbumDelegate();
        try {

            asistenteDelegate.crearAlbum(albumDTO);

            List todos = asistenteDelegate.listarAlbums();
            AlbumDTO asistenteTemp = (AlbumDTO) todos.get(todos.size() - 1);

            return "/album/lista.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            error("errorCrearAlbum", "Error al crear asistente");
            return "/album/lista.xhtml";
        }
    }

    public String actualizar() {
        AlbumDelegate asistenteDelegate = new AlbumDelegate();

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
        AlbumDelegate asistenteDelegate = new AlbumDelegate();
        try {
            asistenteDelegate.elimina(albumDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "/album/lista.xhtml";
    }

    public List getLista() throws SQLException {
        AlbumDelegate asistenteDelegate = new AlbumDelegate();
        try {

        } catch (Exception e) {
            e.printStackTrace();
            error("ErrorListaAlbums", "Error al mostrar articulos");
            //return null;
        }
        return asistenteDelegate.listarAlbums();
    }
    
    
    public List<ArtistDTO> getListaArtistas() {
        ArtistDelegate eventoDelegate = new ArtistDelegate();
        try {

            return eventoDelegate.listarArtists();
        } catch (Exception e) {
            e.printStackTrace();
            error("ErrorListaAsistentes", "Error al mostrar articulos");
            return null;
        }
    }

    public void seleccionaAlbum(ActionEvent event) {
        AlbumDelegate asistenteDelegate = new AlbumDelegate();
        String claveArtSel = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("claveArtSel");
        albumDTO = new AlbumDTO();
        albumDTO.getEntidad().setAlbumid(Integer.parseInt(claveArtSel));
        try {
            albumDTO = asistenteDelegate.leerAlbum(albumDTO);
//            System.out.println(asistenteDTO.getEntidad().getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getIdAlbum() {
//        System.out.println(asistenteDTO.getEntidad().toString());
        try{
            return albumDTO.getEntidad().getAlbumid() + "";
        } catch(NullPointerException e) {
            return 0 + "";
        }

    }

    public void setIdAlbum(String idAlbum) {
        albumDTO.getEntidad().setAlbumid(Integer.parseInt(idAlbum));
    }
    
    
    public Integer getArtist() {
//        System.out.println(asistenteDTO.getEntidad().toString());
        try{
            System.out.println("Dando artista del album: "+albumDTO.getEntidad().getArtist().getName());
            return albumDTO.getEntidad().getArtist().getArtistid();
        } catch(NullPointerException e) {
            return 0;
        }

    }

    public void setArtist(Integer idAlbum) {
        try {
            System.out.println("Ajustando artista del album: ");
            ArtistDelegate artdel=new ArtistDelegate();
            Artist art=artdel.leerArtist(new ArtistDTO(new Artist(idAlbum))).getEntidad();
            albumDTO.getEntidad().setArtist(art);
        } catch (SQLException ex) {
            Logger.getLogger(AlbumBean.class.getName()).log(Level.SEVERE, null, ex);
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

}
