package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jonat
 */
public class CategoriaDAO {
    private static final String SQL_INSERT = "INSERT INTO categoria(nombrecategoria, descripcioncategoria)"
            + "values (?, ?)";
    private static final String SQL_UPDATE = "UPDATE alumno SET nombrecategoria=?, descripcioncategoria=? where noboleta=?";
    private static final String SQL_DELETE = "DELETE FROM categoria where idcategoria=?";
    private static final String SQL_SELECT = "SELECT * FROM categoria where nombrecategoria=?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM categoria";

    private static final String SQL_DATOS = "{call spProductosPorcategoria()}";

    private Connection con;

    public CategoriaDAO() {
    }
    
    public void create(Categoria c) throws Exception {
        PreparedStatement ps = null;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1, c.getNombreCategoria());
            ps.setString(2, c.getDescripcionCategoria());
            
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
                con.close();
            }
        }
    }

    public void update(Categoria c) throws Exception {
        PreparedStatement ps = null;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombreCategoria());
            ps.setString(2, c.getDescripcionCategoria());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public void delete(Categoria c) throws Exception {
        PreparedStatement ps = null;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1, c.getIdcategoria());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public Categoria read(int id) throws Exception {
        PreparedStatement ps = null;
        Boolean respuesta;
        Categoria cr = new Categoria();
        ResultSet rs1 = null;
        //CarreraDAO carrDao = new CarreraDAO();
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_SELECT);
            ps.setInt(1, id);
            rs1 = ps.executeQuery();
            while (rs1.next()) {
                cr.setIdcategoria(rs1.getInt(1));
                cr.setNombreCategoria(rs1.getString(2));
                cr.setDescripcionCategoria(rs1.getString(3));
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

    public ArrayList<Categoria> readAll() throws Exception {
        PreparedStatement ps = null;
        Boolean respuesta;
        ArrayList<Categoria> categoriasFound = new ArrayList<>();
        Categoria cr = new Categoria();
        ResultSet rs1 = null;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_SELECT_ALL);

            respuesta = ps.execute();
            rs1 = ps.getResultSet();
            while (rs1.next()) {
                cr.setIdcategoria(rs1.getInt(1));
                cr.setNombreCategoria(rs1.getString(2));
                cr.setDescripcionCategoria(rs1.getString(3));

                categoriasFound.add(cr);
                cr = new Categoria();
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return categoriasFound;
    }

    public ArrayList<Datos> getCategoriasEnCadaCarrera() throws SQLException {
        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        ArrayList<Datos> lista = new ArrayList();
        try {
            con = Conexion.getConexion();
            cs = con.prepareCall(SQL_DATOS);
            rs = cs.executeQuery();

            while (rs.next()) {
                Datos datos = new Datos();
                datos.setValor(Integer.parseInt(rs.getString("Categorias")));
                datos.setAtributo(rs.getString("carrera"));
                lista.add(datos);
            }

        } finally {
            if (cs != null) {
                cs.close();
            }
            if (rs != null) {
                rs.close();
            }

        }
        return lista;
    }
    
}
