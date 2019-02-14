package objectAndDao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlumnoDAO {

    private static final String SQL_INSERT = "INSERT INTO alumno(nombre, materno, paterno, domicilio, email, idcarrera)"
            + "values (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE alumno SET nombre=?, materno=?, paterno=?, domicilio=?, email=?, idcarrera=? "
            + "where noboleta=?";
    private static final String SQL_DELETE = "DELETE FROM alumno where noboleta=?";
    private static final String SQL_SELECT = "SELECT * FROM alumno where noboleta=?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM alumno";

    private static final String SQL_DATOS = "{call spAlumnosPorCarrera()}";

    private Connection con;

    public AlumnoDAO() {

    }

    public void create(Alumno c) throws Exception {
        PreparedStatement ps = null;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getMaterno());
            ps.setString(3, c.getPaterno());
            ps.setString(4, c.getDomicilio());
            ps.setString(5, c.getEmail());
            ps.setInt(6, c.getCarrera().getIdCarrera());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
                con.close();
            }
        }
    }

    public void update(Alumno c) throws Exception {
        PreparedStatement ps = null;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getMaterno());
            ps.setString(3, c.getPaterno());
            ps.setString(4, c.getDomicilio());
            ps.setString(5, c.getEmail());
            ps.setInt(6, c.getCarrera().getIdCarrera());
            ps.setInt(7, c.getNoboleta());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public void delete(Alumno c) throws Exception {
        PreparedStatement ps = null;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1, c.getNoboleta());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public Alumno read(int id) throws Exception {
        PreparedStatement ps = null;
        Boolean respuesta;
        Alumno cr = new Alumno();
        ResultSet rs1 = null;
        CarreraDAO carrDao = new CarreraDAO();
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_SELECT);
            ps.setInt(1, id);
            rs1 = ps.executeQuery();
            while (rs1.next()) {
                cr.setNoboleta(rs1.getInt(1));
                cr.setNombre(rs1.getString(2));
                cr.setMaterno(rs1.getString(3));
                cr.setPaterno(rs1.getString(4));
                cr.setDomicilio(rs1.getString(5));
                cr.setEmail(rs1.getString(6));
                cr.setCarrera(carrDao.read(rs1.getInt(7)));
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

    public ArrayList<Alumno> readAll() throws Exception {
        PreparedStatement ps = null;
        Boolean respuesta;
        ArrayList<Alumno> carrerasFound = new ArrayList<>();
        Alumno cr = new Alumno();
        ResultSet rs1 = null;
        CarreraDAO carrDao = new CarreraDAO();
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_SELECT_ALL);

            respuesta = ps.execute();
            rs1 = ps.getResultSet();
            while (rs1.next()) {
                cr.setNoboleta(rs1.getInt(1));
                cr.setNombre(rs1.getString(2));
                cr.setMaterno(rs1.getString(3));
                cr.setPaterno(rs1.getString(4));
                cr.setDomicilio(rs1.getString(5));
                cr.setEmail(rs1.getString(6));
                cr.setCarrera(carrDao.read(rs1.getInt(7)));

                carrerasFound.add(cr);
                cr = new Alumno();
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return carrerasFound;
    }

    public ArrayList<Datos> getAlumnosEnCadaCarrera() throws SQLException {
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
                datos.setValor(Integer.parseInt(rs.getString("Alumnos")));
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

    public static void main(String[] args) {
        AlumnoDAO cdao = new AlumnoDAO();
        try {
            //cdao.create(new Alumno(1, "ndnnd", "isc", 10));

            System.out.println(cdao.getAlumnosEnCadaCarrera().get(1));
        } catch (Exception ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
