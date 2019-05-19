/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.EventoDTO;
import entidades.Evento;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonat
 */
public class EventoDAO {

    private static final String SQL_INSERT
            = "INSERT INTO Evento ("
            + "nombreEvento, inicio, fin, observaciones"
            + ") VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECT
            = "SELECT *"
            + "FROM Evento where idEvento = ?";
    private static final String SQL_SELECT_All
            = "SELECT *"
            + "FROM Evento";
    private static final String SQL_UPDATE
            = "UPDATE Evento SET "
            + "nombreEvento=?, inicio=?, fin=?, observaciones=?"
            + " WHERE "
            + "idEvento = ? ";

    /* SQL to delete data */
    private static final String SQL_DELETE
            = "DELETE FROM Articulo WHERE "
            + "idEvento = ?";

    public void create(EventoDTO dto, Connection conn) throws SQLException {

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_INSERT);

            ps.setString(1, dto.getEntidad().getNombreEvento());
            ps.setDate(2, new java.sql.Date( dto.getEntidad().getInicio().getTime()));
            ps.setDate(3, new java.sql.Date( dto.getEntidad().getFin().getTime()));
            ps.setString(4, dto.getEntidad().getObservaciones());
            
            ps.executeUpdate();
        } finally {
            cerrar(ps);
            cerrar(conn);
        }
    }

    public EventoDTO load(EventoDTO dto, Connection conn) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_SELECT);
            ps.setString(1, dto.getEntidad().getIdEvento().toString());
            rs = ps.executeQuery();
            List results = getResults(rs);
            if (results.size() > 0) {
                return (EventoDTO) results.get(0);
            } else {
                return null;
            }
        } finally {
            cerrar(rs);
            cerrar(ps);
            cerrar(conn);
        }
    }

    public List<EventoDTO> loadAll(Connection conn) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_SELECT_All);
            rs = ps.executeQuery();
            List results = getResults(rs);
            if (results.size() > 0) {
                return results;
            } else {
                return null;
            }
        } finally {
            //cerrar(rs);
            //cerrar(ps);
            //cerrar(conn);
        }
    }

    public void update(EventoDTO dto, Connection conn) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_UPDATE);

            ps.setInt(1, dto.getEntidad().getIdEvento());
            
            ps.setString(2, dto.getEntidad().getNombreEvento());
            ps.setDate(3, (Date) dto.getEntidad().getInicio());
            ps.setDate(4, (Date) dto.getEntidad().getFin());
            ps.setString(5, dto.getEntidad().getObservaciones());

            ps.executeUpdate();
        } finally {
            cerrar(ps);
            cerrar(conn);
        }
    }

    public void delete(EventoDTO dto, Connection conn) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdEvento());
            ps.executeUpdate();
        } finally {
            cerrar(ps);
            cerrar(conn);
        }
    }

    private List getResults(ResultSet rs) throws SQLException {
        List results = new ArrayList();
        while (rs.next()) {
            EventoDTO dto = new EventoDTO();
            dto.getEntidad().setIdEvento(rs.getInt(1));
            dto.getEntidad().setNombreEvento(rs.getString(2));
            dto.getEntidad().setInicio(rs.getDate(3));
            dto.getEntidad().setFin(rs.getDate(4));
            dto.getEntidad().setObservaciones(rs.getString(5));
            
            results.add(dto);
        }
        return results;
    }

    private void cerrar(PreparedStatement ps) throws SQLException {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
            }
        }
    }

    private void cerrar(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
            }
        }
    }

    private void cerrar(Connection cnn) {
        if (cnn != null) {
            try {
                cnn.close();
            } catch (SQLException e) {
            }
        }
    }

}
