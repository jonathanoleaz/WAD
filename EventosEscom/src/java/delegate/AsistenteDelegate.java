/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delegate;

import dto.AsistenteDTO;
import facade.AsistenteFacade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author asuncion
 */
public class AsistenteDelegate {

    private Connection cnn;
    private AsistenteFacade artFacade;

    public AsistenteDelegate() {

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
        artFacade = new AsistenteFacade(cnn);
    }

    //Codigo para los Asistentes
    public void crearAsistente(AsistenteDTO dto) throws SQLException {
        artFacade.crear(dto);
    }

    public List listarAsistentes() throws SQLException {
        return artFacade.listar();
    }

    public AsistenteDTO leerAsistente(AsistenteDTO dto) throws SQLException {
        return artFacade.leer(dto);
    }

    public void actualiza(AsistenteDTO dto) throws SQLException {
        System.out.println("se actualiza");
        System.out.println(dto.getEntidad().getSemestre());
        artFacade.actualiza(dto);
    }

    public void elimina(AsistenteDTO dto) throws SQLException {
        artFacade.elimina(dto);
    }
}
