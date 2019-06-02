/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.beans;

import java.sql.SQLException;
import model.delegate.MediatypeDelegate;

import model.dto.MediatypeDTO;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import static vista.beans.BaseBean.ACC_CREAR;


/* @author Asuncion */
@ManagedBean(name = "MediatypeBean")
@SessionScoped
public class MediatypeBean extends BaseBean {

    private MediatypeDTO artistDTO;

    public MediatypeBean() {
    }

    public String nuevo() {
        artistDTO = new MediatypeDTO();
        setAccion(ACC_CREAR);
        return "/mediatype/nuevo.xhtml";
    }

    public String editar() {
        setAccion(ACC_ACTUALIZAR);
        return "/mediatype/editar.xhtml";
    }

    public String crear() {
        MediatypeDelegate asistenteDelegate = new MediatypeDelegate();
        try {

            asistenteDelegate.crearMediatype(artistDTO);

            List todos = asistenteDelegate.listarMediatypes();
            MediatypeDTO asistenteTemp = (MediatypeDTO) todos.get(todos.size() - 1);

            return "/mediatype/lista.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            error("errorCrearMediatype", "Error al crear asistente");
            return "/mediatype/lista.xhtml";
        }
    }

    public String actualizar() {
        MediatypeDelegate asistenteDelegate = new MediatypeDelegate();

       // System.out.println(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().toString());
        try {

            asistenteDelegate.actualiza(artistDTO);
            return "/mediatype/lista.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            error("errorCrearAlumno", "Error al crear articulo");
            return "/mediatype/lista.xhtml";
        }
    }

    public String borrar() {
        MediatypeDelegate asistenteDelegate = new MediatypeDelegate();
        try {
            asistenteDelegate.elimina(artistDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "/mediatype/lista.xhtml";
    }

    public List getLista() throws SQLException {
        MediatypeDelegate asistenteDelegate = new MediatypeDelegate();
        try {

        } catch (Exception e) {
            e.printStackTrace();
            error("ErrorListaMediatypes", "Error al mostrar articulos");
            //return null;
        }
        return asistenteDelegate.listarMediatypes();
    }

    public void seleccionaMediatype(ActionEvent event) {
        MediatypeDelegate asistenteDelegate = new MediatypeDelegate();
        String claveArtSel = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap()
                .get("claveArtSel");
        artistDTO = new MediatypeDTO();
        artistDTO.getEntidad().setMediatypeid(Integer.parseInt(claveArtSel));
        try {
            artistDTO = asistenteDelegate.leerMediatype(artistDTO);
//            System.out.println(asistenteDTO.getEntidad().getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getIdMediatype() {
//        System.out.println(asistenteDTO.getEntidad().toString());
        try{
            return artistDTO.getEntidad().getMediatypeid() + "";
        } catch(NullPointerException e) {
            return 0 + "";
        }

    }

    public void setIdMediatype(String idMediatype) {
        artistDTO.getEntidad().setMediatypeid(Integer.parseInt(idMediatype));
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
