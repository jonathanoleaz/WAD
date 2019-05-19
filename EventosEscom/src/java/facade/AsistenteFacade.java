package facade;


import dao.AsistenteDAO;
import dto.AsistenteDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AsistenteFacade {

    private Connection cnn;
    private AsistenteDAO dao;

    public AsistenteFacade(Connection cnn) {
        this.cnn = cnn;
        dao = new AsistenteDAO();
    }

    public void crear(AsistenteDTO dto) throws SQLException {
        dao.create(dto, cnn);
    }

    public List listar() throws SQLException {
        return dao.loadAll(cnn);
    }

    public AsistenteDTO leer(AsistenteDTO dto)throws SQLException {
        return dao.load(dto, cnn);
    }

    public void actualiza(AsistenteDTO dto)throws SQLException {
         dao.update(dto, cnn);
    }

    public void elimina(AsistenteDTO dto)throws SQLException {
         dao.delete(dto, cnn);
    }
}