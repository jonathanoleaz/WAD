package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ArticuloDAO {
    private static final String SQL_INSERT = "INSERT INTO articulo(clavearticulo, nombreproducto, descripcionproducto, "
            + "precio, existencia, idcategoria)"
            + "values (?, ?)";
    private static final String SQL_UPDATE = "UPDATE articulo SET nombreproducto=?, descripcionproducto=?, precio=?, existencia=?, idcategoria=? where clavearticulo=?";
    private static final String SQL_DELETE = "DELETE FROM articulo where clavearticulo=?";
    private static final String SQL_SELECT = "SELECT * FROM articulo where clavearticulo=?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM articulo";

    private static final String SQL_DATOS = "{call spProductosPorcategoria()}";

    private Connection con;

    public ArticuloDAO() {
    }
    
    public void create(Articulo c) throws Exception {
        PreparedStatement ps = null;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1, c.getClavearticulo());
            ps.setString(2, c.getNombreproducto());
            ps.setString(3, c.getDescripcionproducto());
            ps.setDouble(4, c.getPrecio());
            ps.setInt(5, c.getExistencia());
            ps.setInt(6, c.getIdcategoria());
            
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
                con.close();
            }
        }
    }

    public void update(Articulo c) throws Exception {
        PreparedStatement ps = null;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_INSERT);
            
            ps.setString(1, c.getNombreproducto());
            ps.setString(2, c.getDescripcionproducto());
            ps.setDouble(3, c.getPrecio());
            ps.setInt(4, c.getExistencia());
            ps.setInt(5, c.getIdcategoria());
            
            ps.setString(6, c.getClavearticulo());
            
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public void delete(Articulo c) throws Exception {
        PreparedStatement ps = null;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setString(1, c.getClavearticulo());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public Articulo read(int id) throws Exception {
        PreparedStatement ps = null;
        Boolean respuesta;
        Articulo cr = new Articulo();
        ResultSet rs1 = null;
        //CarreraDAO carrDao = new CarreraDAO();
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_SELECT);
            ps.setInt(1, id);
            rs1 = ps.executeQuery();
            while (rs1.next()) {
                cr.setClavearticulo(rs1.getString(1));
                cr.setNombreproducto(rs1.getString(2));
                cr.setDescripcionproducto(rs1.getString(3));
                cr.setPrecio(rs1.getDouble(4));
                cr.setExistencia(rs1.getInt(5));
                cr.setIdcategoria(rs1.getInt(6));
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

    public ArrayList<Articulo> readAll() throws Exception {
        PreparedStatement ps = null;
        Boolean respuesta;
        ArrayList<Articulo> categoriasFound = new ArrayList<>();
        Articulo cr = new Articulo();
        ResultSet rs1 = null;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_SELECT_ALL);

            respuesta = ps.execute();
            rs1 = ps.getResultSet();
            while (rs1.next()) {
                
                cr.setClavearticulo(rs1.getString(1));
                cr.setNombreproducto(rs1.getString(2));
                cr.setDescripcionproducto(rs1.getString(3));
                cr.setPrecio(rs1.getDouble(4));
                cr.setExistencia(rs1.getInt(5));
                cr.setIdcategoria(rs1.getInt(6));

                categoriasFound.add(cr);
                cr = new Articulo();
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return categoriasFound;
    }

    public ArrayList<Datos> getArticulosEnCadaCategoria() throws SQLException {
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
                datos.setValor(Integer.parseInt(rs.getString("Articulos")));
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
