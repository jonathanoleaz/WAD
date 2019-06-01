package model.facade;


import model.dao.ArtistDAO;
import model.dto.ArtistDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ArtistFacade {

    private Connection cnn;
    private ArtistDAO dao;

    public ArtistFacade() {
        //this.cnn = cnn;
        dao = new ArtistDAO();
    }

    public void crear(ArtistDTO dto) throws SQLException {
        dao.createRecord(dto);
    }

    public List listar() throws SQLException {
        return dao.displayRecords();
    }

    public ArtistDTO leer(ArtistDTO dto)throws SQLException {
        return dao.findRecordById(dto.getEntidad().getArtistid());
    }

    public void actualiza(ArtistDTO dto)throws SQLException {
         dao.updateRecord(dto);
    }

    public void elimina(ArtistDTO dto)throws SQLException {
         dao.deleteRecord(dto);
    }
    
   
}