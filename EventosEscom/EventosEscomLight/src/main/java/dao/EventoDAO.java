package dao;

import dto.EventoDTO;
import entidades.Evento;
import java.sql.Connection;
import java.sql.Timestamp;
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
            = "DELETE FROM Evento WHERE "
            + "idEvento = ?";

    public void create(EventoDTO dto, Connection conn) {

        PreparedStatement ps = null;
        try {
            System.out.println("Insertando evento");
            ps = conn.prepareStatement(SQL_INSERT);
            
            ps.setString(1, dto.getEntidad().getNombreEvento());
            ps.setTimestamp(2, new java.sql.Timestamp( dto.getEntidad().getInicio().getTime()));
            ps.setTimestamp(3, new java.sql.Timestamp( dto.getEntidad().getFin().getTime()));
            ps.setString(4, dto.getEntidad().getObservaciones());
            
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR");
            //cerrar(ps);
            //cerrar(conn);
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
            //cerrar(rs);
            //cerrar(ps);
            //cerrar(conn);
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
            
            System.out.println("Fecha convertida: "+new java.sql.Timestamp(dto.getEntidad().getInicio().getTime()).toString());

            ps.setInt(5, dto.getEntidad().getIdEvento());
            
            ps.setString(1, dto.getEntidad().getNombreEvento());
            ps.setTimestamp(2, new java.sql.Timestamp(dto.getEntidad().getInicio().getTime()));
            ps.setTimestamp(3, new java.sql.Timestamp(dto.getEntidad().getFin().getTime()));
            ps.setString(4, dto.getEntidad().getObservaciones());

            ps.executeUpdate();
        } finally {
            //cerrar(ps);
            //cerrar(conn);
        }
    }

    public void delete(EventoDTO dto, Connection conn) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdEvento());
            ps.executeUpdate();
        } finally {
            //cerrar(ps);
            //cerrar(conn);
        }
    }

    private List getResults(ResultSet rs) throws SQLException {
        List results = new ArrayList();
        while (rs.next()) {
            EventoDTO dto = new EventoDTO();
            dto.getEntidad().setIdEvento(rs.getInt(1));
            dto.getEntidad().setNombreEvento(rs.getString(2));
            dto.getEntidad().setInicio(rs.getTimestamp(3));
            dto.getEntidad().setFin(rs.getTimestamp(4));
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
