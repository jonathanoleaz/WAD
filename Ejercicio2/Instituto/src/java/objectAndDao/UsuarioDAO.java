package objectAndDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioDAO {
    private static final String SQL_INSERT = "INSERT INTO usuario(nombre_usuario, password, tipo_usuario)"
            + "values (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET nombre_usuario=?, password=?, tipo_usuario=? "
            + "where idusuario=?";
    private static final String SQL_DELETE = "DELETE FROM usuario where idusuario=?";
    private static final String SQL_SELECT = "SELECT * FROM usuario where idusuario=?";
    private static final String SQL_SELECT_BY_NAME = "SELECT * FROM usuario where nombre_usuario=?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM usuario";

    private Connection con;

    public UsuarioDAO() {
    }

    public void create(Usuario c) throws Exception {
        PreparedStatement ps = null;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1, c.getNombreUsuario());
            ps.setString(2, c.getPassword());
            ps.setInt(3, c.getTipoUsuario());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
                con.close();
            }
        }
    }

    public void update(Usuario c) throws Exception {
        PreparedStatement ps = null;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombreUsuario());
            ps.setString(2, c.getPassword());
            ps.setInt(3, c.getTipoUsuario());
            ps.setInt(4, c.getIdusuario());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public void delete(Usuario c) throws Exception {
        PreparedStatement ps = null;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1, c.getIdusuario());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public Usuario read(int id) throws Exception {
        PreparedStatement ps = null;
        Boolean respuesta;
        Usuario cr = new Usuario();
        ResultSet rs1 = null;
        CarreraDAO carrDao = new CarreraDAO();
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_SELECT);
            ps.setInt(1, id);
            rs1 = ps.executeQuery();
            while (rs1.next()) {
                cr.setIdusuario(rs1.getInt(1));
                cr.setNombreUsuario(rs1.getString(2));
                cr.setPassword(rs1.getString(3));
                cr.setTipoUsuario(rs1.getInt(4));

            }
        } finally {
            if (ps != null) {
                ps.close();
                con.close();
                rs1.close();
            }
        }
        return cr;
    }
    
    public Usuario readByNombre(String nombre) throws Exception {
        PreparedStatement ps = null;
        Boolean respuesta;
        Usuario cr = new Usuario();
        ResultSet rs1 = null;
        CarreraDAO carrDao = new CarreraDAO();
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_SELECT_BY_NAME);
            ps.setString(1, nombre);
            rs1 = ps.executeQuery();
            while (rs1.next()) {
                cr.setIdusuario(rs1.getInt(1));
                cr.setNombreUsuario(rs1.getString(2));
                cr.setPassword(rs1.getString(3));
                cr.setTipoUsuario(rs1.getInt(4));

            }
        } finally {
            if (ps != null) {
                ps.close();
                con.close();
                rs1.close();
            }
        }
        return cr;
    }

    public ArrayList<Usuario> readAll() throws Exception {
        PreparedStatement ps = null;
        Boolean respuesta;
        ArrayList<Usuario> carrerasFound = new ArrayList<>();
        Usuario cr = new Usuario();
        ResultSet rs1 = null;
        CarreraDAO carrDao = new CarreraDAO();
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_SELECT_ALL);

            respuesta = ps.execute();
            rs1 = ps.getResultSet();
            while (rs1.next()) {
                cr.setIdusuario(rs1.getInt(1));
                cr.setNombreUsuario(rs1.getString(2));
                cr.setPassword(rs1.getString(3));
                cr.setTipoUsuario(rs1.getInt(4));

                carrerasFound.add(cr);
                cr = new Usuario();
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return carrerasFound;
    }

    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public static boolean checkPass(String plainPassword, String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        UsuarioDAO cdao = new UsuarioDAO();
        try {

            cdao.create(new Usuario(1, "jonathan", UsuarioDAO.hashPassword("decisiones"), 1));

            System.out.println(UsuarioDAO.checkPass("decisiones", cdao.read(1).getPassword()));
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
