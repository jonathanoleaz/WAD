package alumnoServlets;

import alumnoServlets.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objectAndDao.*;

@WebServlet(name = "MostrarAlumno", urlPatterns = {"/MostrarAlumno"})
public class MostrarAlumno extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        
        if(session.getAttribute("nombreUsuario")==null)
            response.sendRedirect("iniciarSesion");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\"  />"
                    + "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">");
            out.println("<title>Servlet MostrarAlumno</title>");
            out.println("</head>");
            out.println("<body>"
                    + "<ul>\n" +
"    <li><a href=\"iniciarSesion\">Iniciar sesión</a></li>\n" +
"    <li><a href=\"MostrarAlumno\">Alumno</a></li>\n" +
"    <li><a href=\"MostrarCarrera\">Carrera</a></li>\n" +
"\n" +
"</ul>");

            String nombre, apPaterno, apMaterno, domicilio, correo, carrera;
            String mensajeAMostrar = "";
            int idAlumno, duracionAlumno;

            out.println("<h3 align='center'>Lista de Alumnos</h3>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th> Id</th>");
            out.println("<th> Nombre </th>");
            out.println("<th> Ap. paterno</th>");
            out.println("<th> Ap. materno </th>");
            out.println("<th> Domicilio </th>");
            out.println("<th> E-mail </th>");
            out.println("<th> Carrera </th>");
            out.println("<th> Acción </th>");
            out.println("</tr>");

            AlumnoDAO dao = new AlumnoDAO();
            try {
                List<Alumno> lista = dao.readAll();
                for (int i = 0; i < lista.size(); i++) {
                    Alumno listaAlumno = (Alumno) lista.get(i);
                    idAlumno = listaAlumno.getNoboleta();
                    nombre = listaAlumno.getNombre();
                    apPaterno = listaAlumno.getPaterno();
                    apMaterno = listaAlumno.getMaterno();
                    domicilio = listaAlumno.getDomicilio();
                    correo = listaAlumno.getEmail();
                    carrera = listaAlumno.getCarrera().getNombreCarrera();

                    out.println("<tr>");
                    out.println("<td><a href='VerAlumno?id=" + idAlumno + "' >" + idAlumno + "</a></td>");
                    out.println("<td>" + nombre + "</td>");
                    out.println("<td>" + apPaterno + "</td>");
                    out.println("<td>" + apMaterno + "</td>");
                    out.println("<td>" + domicilio + "</td>");
                    out.println("<td>" + correo + "</td>");
                    out.println("<td>" + carrera + "</td>");
                    out.println("<td> <a href='EditarAlumno?id=" + idAlumno + "' > <i style=\"font-size:24px\" class=\"fa\">&#xf044;</i> </a> "
                            + "<a href='EliminarAlumno?id=" + idAlumno + " '> <i style=\"font-size:24px\" class=\"fa\">&#xf014;</i> </a>"
                            + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");

                out.println("<div align='center'>");
                out.println("<a href='GuardarAlumno'> Agregar Alumno </a>");
                out.println("</div>");
            } catch (SQLException e) {
                mensajeAMostrar = "No se pudó mostrar el listado de Alumnos" + e.toString();
                out.println("<div align='center'>");
                out.println(mensajeAMostrar + "<br/><br/>");
                out.println("</div>");
                Logger.getLogger(MostrarAlumno.class.getName()).log(Level.SEVERE, null, e);
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
            Logger.getLogger(MostrarAlumno.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MostrarAlumno.class.getName()).log(Level.SEVERE, null, ex);
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
