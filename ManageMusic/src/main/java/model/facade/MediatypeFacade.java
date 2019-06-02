package model.facade;


import model.dao.MediatypeDAO;
import model.dto.MediatypeDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MediatypeFacade {

    private Connection cnn;
    private MediatypeDAO dao;

    public MediatypeFacade() {
        //this.cnn = cnn;
        dao = new MediatypeDAO();
    }

    public void crear(MediatypeDTO dto) throws SQLException {
        dao.createRecord(dto);
    }

    public List<MediatypeDTO> listar() throws SQLException {
        return dao.displayRecords();
    }

    public MediatypeDTO leer(MediatypeDTO dto)throws SQLException {
        return dao.findRecordById(dto.getEntidad().getMediatypeid());
    }

    public void actualiza(MediatypeDTO dto)throws SQLException {
         dao.updateRecord(dto);
    }

    public void elimina(MediatypeDTO dto)throws SQLException {
         dao.deleteRecord(dto);
    }
    
   
}