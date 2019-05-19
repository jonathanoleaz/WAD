
import dao.AsistenteDAO;
import dao.EventoDAO;
import dto.AsistenteDTO;
import dto.EventoDTO;
import entidades.Asistente;
import entidades.Evento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PruebaDAO {

    public PruebaDAO() {
    }

    public static void main(String[] args) throws SQLException {
        /*
         * Solo para probar el funcionamiento
         */
        Connection cnn = null;
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

        //Crear una Instacia del DTO
        EventoDTO dto = new EventoDTO();
        //Crear una instacia del DAO
        EventoDAO dao = new EventoDAO();
        
        dto=new EventoDTO(new Evento(0, "Contingencia", new Date(2016, 4, 4), new Date(2016, 4, 4)));
        dao.create(dto, cnn);
        
        cnn = DriverManager.getConnection(url, user, pwd);
        
        System.out.println(Arrays.toString(dao.loadAll(cnn).toArray()));
        
        cnn=DriverManager.getConnection(url, user, pwd);
        
        AsistenteDTO dto2=new AsistenteDTO();
        AsistenteDAO dao2=new AsistenteDAO();
        
        dto2=new AsistenteDTO(new Asistente(0, "Jonathan", "Olea", "Zúñiga", "jonathanoleaz@gmail.com", 3, 'H', true));
        dto2.getEntidad().setIdEvento(new Evento(1));
        dao2.create(dto2, cnn);
        
    }
}