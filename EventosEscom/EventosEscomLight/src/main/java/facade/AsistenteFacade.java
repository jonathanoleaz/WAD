package facade;


import dao.AsistenteDAO;
import dto.AsistenteDTO;
import dto.DatosGrafica;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import org.jfree.data.general.DefaultPieDataset;
import org.primefaces.model.chart.PieChartModel;

public class AsistenteFacade {

    private Connection cnn;
    private AsistenteDAO dao;

    public AsistenteFacade(Connection cnn) {
        this.cnn = cnn;
        dao = new AsistenteDAO();
    }

    public void crear(AsistenteDTO dto) throws SQLException {
        dao.create(dto, cnn);
    }

    public List listar() throws SQLException {
        return dao.loadAll(cnn);
    }

    public AsistenteDTO leer(AsistenteDTO dto)throws SQLException {
        return dao.load(dto, cnn);
    }

    public void actualiza(AsistenteDTO dto)throws SQLException {
         dao.update(dto, cnn);
    }

    public void elimina(AsistenteDTO dto)throws SQLException {
         dao.delete(dto, cnn);
    }
    
    public DefaultPieDataset getGraficaAsistente(){
        DefaultPieDataset dps=new DefaultPieDataset();
        try{
            List datos = dao.datosGrafica(cnn);
            for(int indice=0; indice<datos.size(); indice++){
                DatosGrafica dg=(DatosGrafica) datos.get(indice);
                dps.setValue(dg.getEvento(), dg.getCantidad());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return dps;
    }
    
    public PieChartModel getAsistentesByEvent() {
        PieChartModel model;
        Connection conn = null;
        AsistenteDAO dao = new AsistenteDAO();
        List datos = null;

        String user = "root";
        String pwd = "root";
        String url = "jdbc:mysql://localhost:3306/EventosEscom";
        String mySqlDriver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(mySqlDriver);
            conn = DriverManager.getConnection(url, user, pwd);
            datos = dao.datosGrafica(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        model = new PieChartModel();

        for (int indice = 0; indice < datos.size(); indice++) {
            DatosGrafica dg = (DatosGrafica) datos.get(indice);
            model.set(dg.getEvento(), dg.getCantidad());
        }
        /*model.set("Java", 62);//jobs in thousands
         model.set("Python", 46);
         model.set("JavaScript", 38);
         model.set("C++", 31);
         model.set("C#", 27);
         model.set("PHP", 14);
         model.set("Perl", 14);*/

        //followings are some optional customizations:
        //set title
        model.setTitle("Alumnos registrados por evento");
        //set legend position to 'e' (east), other values are 'w', 's' and 'n'
        model.setLegendPosition("w");

        //enable tooltips
        //model.setShowDatatip(true);
        //show labels inside pie chart
        model.setShowDataLabels(true);
        //show label text  as 'value' (numeric) , others are 'label', 'percent' (default). Only one can be used.
        model.setDataFormat("value");
        //format: %d for 'value', %s for 'label', %d%% for 'percent'
        //model.setDataLabelFormatString("%dK");
        //pie sector colors
        model.setSeriesColors("adf,fca,ffa,aff,afa,faa,aaf,acf,aca,aac,caa,cca,acc,cac,aae,aea,eaa,cea,aee,ece");

        return model;
    }
}