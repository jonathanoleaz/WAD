package vista.beans;

import dao.AsistenteDAO;
import dto.DatosGrafica;
import facade.AsistenteFacade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import org.primefaces.model.chart.PieChartModel;

@Named(value = "catalogoBean")
@ApplicationScoped
public class CatalogoBean {

    private PieChartModel model;
    private Connection conn;
    AsistenteFacade artFacade;

    @PostConstruct
    public void init() {
        String user = "root";
        String pwd = "root";
        String url = "jdbc:mysql://localhost:3306/EventosEscom";
        String mySqlDriver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(mySqlDriver);
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        artFacade = new AsistenteFacade(conn);
        
    }
    
    public PieChartModel getModel(){
        return artFacade.getAsistentesByEvent();
    }
    
    
        
        

}
