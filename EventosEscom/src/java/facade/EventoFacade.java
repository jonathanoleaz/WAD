package facade;


import dao.EventoDAO;
import dto.EventoDTO;
import entidades.Evento;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EventoFacade {

    private Connection cnn;
    private EventoDAO dao;

    public EventoFacade(Connection cnn) {
        this.cnn = cnn;
        dao = new EventoDAO();
    }

    public void crear(EventoDTO dto) throws SQLException {
        dao.create(dto, cnn);
    }

    public List<EventoDTO> listar() throws SQLException {
        return dao.loadAll(cnn);
    }

    public EventoDTO leer(EventoDTO dto)throws SQLException {
        return dao.load(dto, cnn);
    }

    public void actualiza(EventoDTO dto)throws SQLException {
         dao.update(dto, cnn);
    }

    public void elimina(EventoDTO dto)throws SQLException {
         dao.delete(dto, cnn);
    }
}