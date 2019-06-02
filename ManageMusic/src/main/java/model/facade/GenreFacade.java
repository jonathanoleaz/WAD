package model.facade;


import model.dao.GenreDAO;
import model.dto.GenreDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GenreFacade {

    private Connection cnn;
    private GenreDAO dao;

    public GenreFacade() {
        //this.cnn = cnn;
        dao = new GenreDAO();
    }

    public void crear(GenreDTO dto) throws SQLException {
        dao.createRecord(dto);
    }

    public List<GenreDTO> listar() throws SQLException {
        return dao.displayRecords();
    }

    public GenreDTO leer(GenreDTO dto)throws SQLException {
        return dao.findRecordById(dto.getEntidad().getGenreid());
    }

    public void actualiza(GenreDTO dto)throws SQLException {
         dao.updateRecord(dto);
    }

    public void elimina(GenreDTO dto)throws SQLException {
         dao.deleteRecord(dto);
    }
    
   
}