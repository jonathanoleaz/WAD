/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class BaseBean {
        protected static final String ACC_CREAR = "CREAR";
        protected static final String ACC_ACTUALIZAR = "ACTUALIZAR";

        protected String accion;

        public BaseBean() {
        }

        protected void error(String idMensaje, String mensaje) {
                FacesContext.getCurrentInstance().addMessage(
                                idMensaje,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje,
                                                idMensaje));
        }

        public void setAccion(String accion) {
                this.accion = accion;
        }

        public String getAccion() {
                return accion;
        }

        public boolean isModoCrear() {
                if (accion != null) {
                        return accion.equals(ACC_CREAR);
                } else {
                        return false;
                }
        }

        public boolean isModoActualizar() {
                if (accion != null) {
                        return accion.equals(ACC_ACTUALIZAR);
                } else {
                        return false;
                }

        }
}