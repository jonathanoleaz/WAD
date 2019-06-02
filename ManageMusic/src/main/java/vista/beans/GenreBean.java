/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.beans;

import java.sql.SQLException;
import model.delegate.GenreDelegate;

import model.dto.GenreDTO;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import static vista.beans.BaseBean.ACC_CREAR;


/* @author Asuncion */
@ManagedBean(name = "GenreBean")
@SessionScoped
public class GenreBean extends BaseBean {

    private GenreDTO artistDTO;

    public GenreBean() {
    }

    public String nuevo() {
        artistDTO = new GenreDTO();
        setAccion(ACC_CREAR);
        return "/genre/nuevo.xhtml";
    }

    public String editar() {
        setAccion(ACC_ACTUALIZAR);
        return "/genre/editar.xhtml";
    }

    public String crear() {
        GenreDelegate asistenteDelegate = new GenreDelegate();
        try {

            asistenteDelegate.crearGenre(artistDTO);

            List todos = asistenteDelegate.listarGenres();
            GenreDTO asistenteTemp = (GenreDTO) todos.get(todos.size() - 1);

            return "/genre/lista.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            error("errorCrearGenre", "Error al crear asistente");
            return "/genre/lista.xhtml";
        }
    }

    public String actualizar() {
        GenreDelegate asistenteDelegate = new GenreDelegate();

       // System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().toString());
        try {

            asistenteDelegate.actualiza(artistDTO);
            return "/genre/lista.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            error("errorCrearAlumno", "Error al crear articulo");
            return "/genre/lista.xhtml";
        }
    }

    public String borrar() {
        GenreDelegate asistenteDelegate = new GenreDelegate();
        try {
            asistenteDelegate.elimina(artistDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "/genre/lista.xhtml";
    }

    public List getLista() throws SQLException {
        GenreDelegate asistenteDelegate = new GenreDelegate();
        try {

        } catch (Exception e) {
            e.printStackTrace();
            error("ErrorListaGenres", "Error al mostrar articulos");
            //return null;
        }
        return asistenteDelegate.listarGenres();
    }

    public void seleccionaGenre(ActionEvent event) {
        GenreDelegate asistenteDelegate = new GenreDelegate();
        String claveArtSel = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("claveArtSel");
        artistDTO = new GenreDTO();
        artistDTO.getEntidad().setGenreid(Integer.parseInt(claveArtSel));
        try {
            artistDTO = asistenteDelegate.leerGenre(artistDTO);
//            System.out.println(asistenteDTO.getEntidad().getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getIdGenre() {
//        System.out.println(asistenteDTO.getEntidad().toString());
        try{
            return artistDTO.getEntidad().getGenreid() + "";
        } catch(NullPointerException e) {
            return 0 + "";
        }

    }

    public void setIdGenre(String idGenre) {
        artistDTO.getEntidad().setGenreid(Integer.parseInt(idGenre));
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
