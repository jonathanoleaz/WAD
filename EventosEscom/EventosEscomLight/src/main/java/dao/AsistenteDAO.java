/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.AsistenteDTO;
import dto.DatosGrafica;
import entidades.Evento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonat
 */
public class AsistenteDAO {

    private static final String SQL_INSERT =
            "INSERT INTO Asistente ("
            + "nombreAsistente, paternoAsistente, maternoAsistente, idEvento, emailAsistente, semestre, genero, activo, observaciones"
            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT =
            "SELECT *"
            + "FROM Asistente where idAsistente = ?";
    private static final String SQL_SELECT_All =
            "SELECT *"
            + "FROM Asistente";
    private static final String SQL_UPDATE =
            "UPDATE Asistente SET "
            + "nombreAsistente=?, paternoAsistente=?, maternoAsistente=?, idEvento=?, emailAsistente=?, semestre=?, genero=?, activo=?, observaciones=?"
            + " WHERE "
            + "idAsistente = ? ";

    /* SQL to delete data */
    private static final String SQL_DELETE =
            "DELETE FROM Asistente WHERE "
            + "idAsistente = ?";
    
    private static final String SQL_GRAFICA=
            "SELECT e.nombreEvento, COUNT(*)"
            + "AS total from Asistente a,"
            + "Evento e "
            + "where e.idEvento=a.idEvento"
            + " GROUP BY a.idEvento";

    public void create(AsistenteDTO dto, Connection conn) throws SQLException {

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_INSERT);
            
            ps.setString(1, dto.getEntidad().getNombreAsistente());
            ps.setString(2, dto.getEntidad().getPaternoAsistente());
            ps.setString(3, dto.getEntidad().getMaternoAsistente());
            ps.setInt(4,    dto.getEntidad().getIdEvento().getIdEvento());
            ps.setString(5, dto.getEntidad().getEmailAsistente());
            ps.setInt(6,    dto.getEntidad().getSemestre());
            ps.setString(7, dto.getEntidad().getGenero().toString());
            ps.setBoolean(8,dto.getEntidad().getActivo());
            ps.setString(9, dto.getEntidad().getObservaciones());
            
            ps.executeUpdate();
        } finally {
            //cerrar(ps);
            //cerrar(conn);
        }
    }

    public AsistenteDTO load(AsistenteDTO dto, Connection conn) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_SELECT);
            ps.setString(1, dto.getEntidad().getIdAsistente().toString());
            rs = ps.executeQuery();
            List results = getResults(rs);
            if (results.size() > 0) {
                return (AsistenteDTO) results.get(0);
            } else {
                return null;
            }
        } finally {
            //cerrar(rs);
            //cerrar(ps);
            //cerrar(conn);
        }
    }

    public List loadAll(Connection conn) throws SQLException {
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

    public void update(AsistenteDTO dto, Connection conn) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_UPDATE);
            
            ps.setInt(10, dto.getEntidad().getIdAsistente());
            ps.setString(1, dto.getEntidad().getNombreAsistente());
            ps.setString(2, dto.getEntidad().getPaternoAsistente());
            ps.setString(3, dto.getEntidad().getMaternoAsistente());
            ps.setInt(4, dto.getEntidad().getIdEvento().getIdEvento());
            ps.setString(5, dto.getEntidad().getEmailAsistente());
            ps.setInt(6, dto.getEntidad().getSemestre());
            ps.setString(7, dto.getEntidad().getGenero().toString());
            ps.setBoolean(8, dto.getEntidad().getActivo());
            ps.setString(9, dto.getEntidad().getObservaciones());
            
            ps.executeUpdate();
            System.out.println("nada nada: "+dto.getEntidad().getIdAsistente());
            System.out.println("nada nada: "+dto.getEntidad().getSemestre());
            System.out.println("Evto: "+dto.getEntidad().getIdEvento().getIdEvento());
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        
    }

    public void delete(AsistenteDTO dto, Connection conn) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdAsistente());
            ps.executeUpdate();
        } finally {
            //cerrar(ps);
            //cerrar(conn);
        }
    }

    private List getResults(ResultSet rs) throws SQLException {
        List results = new ArrayList();
        while (rs.next()) {
            AsistenteDTO dto = new AsistenteDTO();
            dto.getEntidad().setIdAsistente(rs.getInt(1));
            dto.getEntidad().setNombreAsistente(rs.getString(2));
            dto.getEntidad().setPaternoAsistente(rs.getString(3));
            dto.getEntidad().setMaternoAsistente(rs.getString(4));
            dto.getEntidad().setIdEvento(new Evento(rs.getInt(5)));
            dto.getEntidad().setEmailAsistente(rs.getString(6));
            dto.getEntidad().setSemestre(rs.getInt(7));
            dto.getEntidad().setGenero(rs.getString(8).charAt(0));
            dto.getEntidad().setActivo(rs.getBoolean(9));
            dto.getEntidad().setObservaciones(rs.getString(10));
            
            results.add(dto);
        }
        return results;
    }
    
    public List datosGrafica(Connection conn) throws SQLException{
        PreparedStatement ps=null;
        ResultSet rs=null;
        List results=new ArrayList();
        
        try{
            ps=conn.prepareStatement(SQL_GRAFICA);
            rs=ps.executeQuery();
            while(rs.next()){
                DatosGrafica dg=new DatosGrafica();
                dg.setEvento(rs.getString(1));
                dg.setCantidad(rs.getInt(2));
                results.add(dg);
            }
        }
            finally{
                    //cerrar(rs);
            //cerrar(ps);
            //cerrar(conn);
            
        
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
