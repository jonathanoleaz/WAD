package Tiendita;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.ArticuloDAO;
import DAO.Datos;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class ProductosPorCategoria extends HttpServlet {

    private DefaultPieDataset getGraficaAlumnos() {
        DefaultPieDataset pie = new DefaultPieDataset();

        ArticuloDAO dao = new ArticuloDAO();
        try {
            List datos = dao.getArticulosEnCadaCategoria();

            for (int i = 0; i < datos.size(); i++) {
                Datos d = (Datos) datos.get(i);
                pie.setValue(d.getAtributo(), d.getValor());

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pie;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        JFreeChart chart = ChartFactory.createPieChart("Productos by categoría", getGraficaAlumnos(), true, true, Locale.getDefault());
        String archivo = getServletConfig().getServletContext().getRealPath("grafica.png");

        ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("");

        System.out.println(appPath);
        File f = new File(appPath + "/grafica.png");
        ChartUtilities.saveChartAsPNG(f, chart, 1400, 800);

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\"  />"
                    + "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">");

            out.println("<title>Estadísticas</title>");
            out.println("</head>");
            out.println("<body>" + "<ul>\n"
                    + "    <li><a href=\"iniciarSesion\">Iniciar sesión</a></li>\n"
                    + "    <li><a href=\"MostrarAlumno\">Alumno</a></li>\n"
                    + "    <li><a href=\"MostrarCarrera\">Carrera</a></li>\n"
                    + "\n"
                    + "</ul>");
            out.println("<img src=\"grafica.png\" alt=\"GraficoAlumnosByCarrera\" style=\"width:1000px;height:600px;\">");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
