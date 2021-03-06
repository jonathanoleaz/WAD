/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonat
 */
public class CarreraDAO {

    private static final String SQL_INSERT = "INSERT INTO carrera(nombrecarrera, descripcion, duracion)"
            + "values (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE CARRERA SET nombrecarrera=?, descripcion=?, duracion=? "
            + "where idcarrera=?";
    private static final String SQL_DELETE = "DELETE FROM carrera where idcarrera=?";
    private static final String SQL_SELECT = "SELECT * FROM carrera where idcarrera=?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM carrera";

    private Connection con;

    public CarreraDAO() {

    }

    public void create(Carrera c) throws Exception {
        PreparedStatement ps = null;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1, c.getNombrecarrera());
            ps.setString(2, c.getDescripcion());
            ps.setInt(3, c.getDuracion());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
                con.close();
            }
        }
    }

    public void update(Carrera c) throws Exception {
        PreparedStatement ps = null;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombrecarrera());
            ps.setString(2, c.getDescripcion());
            ps.setInt(3, c.getDuracion());
            ps.setInt(4, c.getIdcarrera());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public void delete(Carrera c) throws Exception {
        PreparedStatement ps = null;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_DELETE);
            ps.setInt(1, c.getIdcarrera());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public Carrera read(int id) throws Exception {
        PreparedStatement ps = null;
        Boolean respuesta;
        Carrera cr = new Carrera();
        ResultSet rs1 = null;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_SELECT);
            ps.setInt(1, id);
            rs1 = ps.executeQuery();            
            while (rs1.next()) {
                cr.setIdcarrera(rs1.getInt(1));
                cr.setNombrecarrera(rs1.getString(2));
                cr.setDescripcion(rs1.getString(3));
                cr.setDuracion(rs1.getInt(4));
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

    public ArrayList<Carrera> readAll() throws Exception {
        PreparedStatement ps = null;
        Boolean respuesta;
        ArrayList<Carrera> carrerasFound = new ArrayList<>();
        Carrera cr = new Carrera();
        ResultSet rs1=null;

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_SELECT_ALL);

            respuesta = ps.execute();
            rs1 = ps.getResultSet();
            while (rs1.next()) {
                cr.setIdcarrera(rs1.getInt(1));
                cr.setNombrecarrera(rs1.getString(2));
                cr.setDescripcion(rs1.getString(3));
                cr.setDuracion(rs1.getInt(4));

                carrerasFound.add(cr);
                cr = new Carrera();
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return carrerasFound;
    }

    public static void main(String[] args) {
        CarreraDAO cdao = new CarreraDAO();
        try {
            //cdao.create(new Carrera(1, "ndnnd", "isc", 10));
            cdao.delete(new Carrera(4, SQL_INSERT, SQL_INSERT, 0));
            System.out.println(cdao.readAll().get(1));
        } catch (Exception ex) {
            Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
