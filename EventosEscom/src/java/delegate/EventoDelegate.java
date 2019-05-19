/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delegate;

import dto.EventoDTO;
import entidades.Evento;
import facade.EventoFacade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author asuncion
 */
public class EventoDelegate {

    private Connection cnn;
    private EventoFacade artFacade;

    public EventoDelegate() {

        String user = "root";
        String pwd = "root";
        String url = "jdbc:mysql://localhost:3306/EventosEscom";
        String mySqlDriver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(mySqlDriver);
            cnn = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        artFacade = new EventoFacade(cnn);
    }

    //Codigo para los Eventos
    public void crearEvento(EventoDTO dto) throws SQLException {
        artFacade.crear(dto);
    }

    public List<EventoDTO> listarEventos() throws SQLException {
        return artFacade.listar();
    }

    public EventoDTO leerEvento(EventoDTO dto) throws SQLException {
        return artFacade.leer(dto);
    }

    public void actualiza(EventoDTO dto) throws SQLException {
        System.out.println("se actualiza");
       
        artFacade.actualiza(dto);
    }

    public void elimina(EventoDTO dto) throws SQLException {
        artFacade.elimina(dto);
    }
}
