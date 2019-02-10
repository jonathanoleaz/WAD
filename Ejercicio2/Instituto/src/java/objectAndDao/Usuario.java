package objectAndDao;

import java.io.Serializable;

public class Usuario implements Serializable{
    private int idusuario;
    private String nombreUsuario;
    private String password;
    private int tipoUsuario;

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario(int idusuario, String nombreUsuario, String password, int tipoUsuario) {
        this.idusuario = idusuario;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario() {
    }

    @Override
    public String toString() {
        return "Usuario{" + "idusuario=" + idusuario + ", nombreUsuario=" + nombreUsuario + ", password=" + password + ", tipoUsuario=" + tipoUsuario + '}';
    }
    
    
    
    
    
}
