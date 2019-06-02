/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.delegate;

import model.dto.TrackDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.facade.TrackFacade;


/**
 *
 * @author asuncion
 */
public class TrackDelegate {

    private Connection cnn;
    private TrackFacade artFacade;

    public TrackDelegate() {

        /*String user = "root";
        String pwd = "root";
        String url = "jdbc:mysql://localhost:3306/EventosEscom";
        String mySqlDriver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(mySqlDriver);
            cnn = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        artFacade = new TrackFacade();
    }

    //Codigo para los Tracks
    public void crearTrack(TrackDTO dto) throws SQLException {
        artFacade.crear(dto);
    }

    public List<TrackDTO> listarTracks() throws SQLException {
        return artFacade.listar();
    }

    public TrackDTO leerTrack(TrackDTO dto) throws SQLException {
        return artFacade.leer(dto);
    }

    public void actualiza(TrackDTO dto) throws SQLException {
        
        artFacade.actualiza(dto);
    }

    public void elimina(TrackDTO dto) throws SQLException {
        artFacade.elimina(dto);
    }

    
}
