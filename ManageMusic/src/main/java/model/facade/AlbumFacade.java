package model.facade;


import model.dao.AlbumDAO;
import model.dto.AlbumDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AlbumFacade {

    private Connection cnn;
    private AlbumDAO dao;

    public AlbumFacade() {
        //this.cnn = cnn;
        dao = new AlbumDAO();
    }

    public void crear(AlbumDTO dto) throws SQLException {
        dao.createRecord(dto);
    }

    public List listar() throws SQLException {
        return dao.displayRecords();
    }

    public AlbumDTO leer(AlbumDTO dto)throws SQLException {
        return dao.findRecordById(dto.getEntidad().getAlbumid());
    }

    public void actualiza(AlbumDTO dto)throws SQLException {
         dao.updateRecord(dto);
    }

    public void elimina(AlbumDTO dto)throws SQLException {
         dao.deleteRecord(dto);
    }
    
   
}