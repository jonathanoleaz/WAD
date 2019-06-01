/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.delegate;

import model.dto.ArtistDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.facade.ArtistFacade;


/**
 *
 * @author asuncion
 */
public class ArtistDelegate {

    private Connection cnn;
    private ArtistFacade artFacade;

    public ArtistDelegate() {

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
        artFacade = new ArtistFacade();
    }

    //Codigo para los Artists
    public void crearArtist(ArtistDTO dto) throws SQLException {
        artFacade.crear(dto);
    }

    public List listarArtists() throws SQLException {
        return artFacade.listar();
    }

    public ArtistDTO leerArtist(ArtistDTO dto) throws SQLException {
        return artFacade.leer(dto);
    }

    public void actualiza(ArtistDTO dto) throws SQLException {
        
        artFacade.actualiza(dto);
    }

    public void elimina(ArtistDTO dto) throws SQLException {
        artFacade.elimina(dto);
    }

    
}
