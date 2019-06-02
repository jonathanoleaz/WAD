package model.facade;


import model.dao.TrackDAO;
import model.dto.TrackDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TrackFacade {

    private Connection cnn;
    private TrackDAO dao;

    public TrackFacade() {
        //this.cnn = cnn;
        dao = new TrackDAO();
    }

    public void crear(TrackDTO dto) throws SQLException {
        dao.createRecord(dto);
    }

    public List listar() throws SQLException {
        return dao.displayRecords();
    }

    public TrackDTO leer(TrackDTO dto)throws SQLException {
        return dao.findRecordById(dto.getEntidad().getTrackid());
    }

    public void actualiza(TrackDTO dto)throws SQLException {
         dao.updateRecord(dto);
    }

    public void elimina(TrackDTO dto)throws SQLException {
         dao.deleteRecord(dto);
    }
    
   
}