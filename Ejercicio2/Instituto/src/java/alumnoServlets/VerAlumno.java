package alumnoServlets;

import alumnoServlets.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objectAndDao.*;

/**
 *
 * @author jonat
 */
@WebServlet(name = "VerAlumno", urlPatterns = {"/VerAlumno"})
public class VerAlumno extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\"  />");
            out.println("<title>Servlet VerAlumno</title>");
            out.println("</head>");
            out.println("<body>"
                    + "<ul>\n" +
"    <li><a href=\"iniciarSesion\">Iniciar sesión</a></li>\n" +
"    <li><a href=\"MostrarAlumno\">Alumno</a></li>\n" +
"    <li><a href=\"MostrarCarrera\">Carrera</a></li>\n" +
"\n" +
"</ul>");

            out.println("<h3 align='center'>Datos de la Alumno</h3>");
            out.println("<table align='center' border='1' width='60%'");
            AlumnoDAO dao = new AlumnoDAO();
            Alumno alumno = new Alumno();
            alumno.setNoboleta(Integer.parseInt(request.getParameter("id")));
            try {
                alumno = dao.read(alumno.getNoboleta());
            } catch (SQLException ex) {
                Logger.getLogger(VerAlumno.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (alumno != null) {
                out.println("<tr>");
                out.println("<th> Id</th><td>" + alumno.getNoboleta() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Nombre</th><td>" + alumno.getNombre() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Ap. paterno </th><td>" + alumno.getPaterno()+ "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Ap. materno </th><td>" + alumno.getMaterno() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Domicilio </th><td>" + alumno.getDomicilio()+ "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> E-mail </th><td>" + alumno.getEmail()+ "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Carrera </th><td>" + alumno.getCarrera().getDescripcion()+ "</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<div align='center'>");
                out.println("<a href='EliminarAlumno?id=" + alumno.getNoboleta() + " '> Eliminar Alumno </a>");
                out.println("&nbsp; &nbsp; &nbsp;");
                out.println("<a href='MostrarAlumno'> Lista de Alumnos </a>");
                out.println("</div>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(VerAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(VerAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
