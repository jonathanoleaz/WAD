/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.delegate;

import model.dto.AlbumDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.facade.AlbumFacade;


/**
 *
 * @author asuncion
 */
public class AlbumDelegate {

    private Connection cnn;
    private AlbumFacade artFacade;

    public AlbumDelegate() {

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
        artFacade = new AlbumFacade();
    }

    //Codigo para los Albums
    public void crearAlbum(AlbumDTO dto) throws SQLException {
        artFacade.crear(dto);
    }

    public List listarAlbums() throws SQLException {
        return artFacade.listar();
    }

    public AlbumDTO leerAlbum(AlbumDTO dto) throws SQLException {
        return artFacade.leer(dto);
    }

    public void actualiza(AlbumDTO dto) throws SQLException {
        
        artFacade.actualiza(dto);
    }

    public void elimina(AlbumDTO dto) throws SQLException {
        artFacade.elimina(dto);
    }

    
}
