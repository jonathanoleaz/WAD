/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.delegate;

import model.dto.GenreDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.facade.GenreFacade;


/**
 *
 * @author asuncion
 */
public class GenreDelegate {

    private Connection cnn;
    private GenreFacade artFacade;

    public GenreDelegate() {

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
        artFacade = new GenreFacade();
    }

    //Codigo para los Genres
    public void crearGenre(GenreDTO dto) throws SQLException {
        artFacade.crear(dto);
    }

    public List<GenreDTO> listarGenres() throws SQLException {
        return artFacade.listar();
    }

    public GenreDTO leerGenre(GenreDTO dto) throws SQLException {
        return artFacade.leer(dto);
    }

    public void actualiza(GenreDTO dto) throws SQLException {
        
        artFacade.actualiza(dto);
    }

    public void elimina(GenreDTO dto) throws SQLException {
        artFacade.elimina(dto);
    }

    
}
