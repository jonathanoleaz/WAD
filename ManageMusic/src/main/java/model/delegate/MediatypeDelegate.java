/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.delegate;

import model.dto.MediatypeDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.facade.MediatypeFacade;


/**
 *
 * @author asuncion
 */
public class MediatypeDelegate {

    private Connection cnn;
    private MediatypeFacade artFacade;

    public MediatypeDelegate() {

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
        artFacade = new MediatypeFacade();
    }

    //Codigo para los Mediatypes
    public void crearMediatype(MediatypeDTO dto) throws SQLException {
        artFacade.crear(dto);
    }

    public List<MediatypeDTO> listarMediatypes() throws SQLException {
        return artFacade.listar();
    }

    public MediatypeDTO leerMediatype(MediatypeDTO dto) throws SQLException {
        return artFacade.leer(dto);
    }

    public void actualiza(MediatypeDTO dto) throws SQLException {
        
        artFacade.actualiza(dto);
    }

    public void elimina(MediatypeDTO dto) throws SQLException {
        artFacade.elimina(dto);
    }

    
}
