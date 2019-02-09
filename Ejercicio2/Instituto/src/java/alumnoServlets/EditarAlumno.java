package alumnoServlets;

import alumnoServlets.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "EditarAlumno", urlPatterns = {"/EditarAlumno"})
public class EditarAlumno extends HttpServlet {

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
        response.setContentType("text/html;charset=ISO-8859-1\"");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\"  />");
            out.println("<title>Servlet VerAlumno</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h3 align='center'>Datos del alumno</h3>");

            CarreraDAO carDao = new CarreraDAO();
            ArrayList<Carrera> carrerasTodas = carDao.readAll();
            AlumnoDAO dao = new AlumnoDAO();
            Alumno alumno = new Alumno();
            alumno.setNoboleta(Integer.parseInt(request.getParameter("id")));
            try {
                alumno = dao.read(alumno.getNoboleta());
            } catch (SQLException ex) {
                Logger.getLogger(VerAlumno.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (alumno != null) {
                out.println("<form action=\"EditarAlumno\" method=\"post\" accept-charset=\"ISO-8859-1\">"
                        + "ID:                      <input type=\"number\" name=\"id\"          value=\"" + alumno.getNoboleta() + "\" > "
                        + "<br>Nombre:              <input type=\"text\"   name=\"nombre\"      value=\"" + alumno.getNombre() + "\"> "
                        + "<br>Ap. paterno:         <input type=\"text\"   name=\"paterno\"     value=\"" + alumno.getPaterno() + "\"> "
                        + "<br>Ap. materno:         <input type=\"text\"   name=\"materno\"     value=\"" + alumno.getMaterno() + "\"> "
                        + "<br>Domicilio:           <input type=\"text\"   name=\"domicilio\"   value=\"" + alumno.getDomicilio() + "\">"
                        + "<br>Correo electrónico:  <input type=\"text\"   name=\"correo\"    value=\"" + alumno.getEmail() + "\">"
                        +"<br>Carrera: "
                        + "<select name=\"carrera\">\n");
                                for(int i=0; i<carrerasTodas.size(); i++)
                                {
                                    out.println("<option value=\""+carrerasTodas.get(i).getIdCarrera()+"\">"+carrerasTodas.get(i).getDescripcion()+"</option>\n");
                                }
                        out.print("</select>"
                        + "<br><input type=\"submit\" value=\"Modificar\">"
                        + "</form>");
            }
            //System.out.println(alumno.getDescripcion());
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void processPostRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\"  />");
            out.println("<title>Servlet EditarAlumno</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h3 align='center'>Modificación de alumno</h3>");

            AlumnoDAO dao = new AlumnoDAO();
            CarreraDAO carrDao = new CarreraDAO();
            String mensajeAMostrar = "";
            Alumno alumno = new Alumno();
            //System.out.println(java.util.Arrays.asList(request.getParameterNames()));
            //System.out.println(request.getParameterNames());
            System.out.println(">> "+request.getParameter("id"));
//            alumno.setNoboleta(Integer.parseInt(request.getParameter("id")));
            
            alumno.setNombre(request.getParameter("nombre"));
            alumno.setMaterno(request.getParameter("materno"));
            alumno.setPaterno(request.getParameter("paterno"));
            alumno.setDomicilio(request.getParameter("domicilio"));
            alumno.setEmail(request.getParameter("correo"));
            alumno.setCarrera(carrDao.read(Integer.parseInt(request.getParameter("carrera"))));
            //alumno.set(request.getParameter("nombre"));
            //alumno.setDescripcion(request.getParameter("descripcion"));
            //alumno.setDuracion(Integer.parseInt(request.getParameter("duracion")));
            try {
                dao.create(alumno);
                mensajeAMostrar = "El Registro se editó satisfactoriamente";
            } catch (SQLException ex) {
                Logger.getLogger(VerAlumno.class.getName()).log(Level.SEVERE, null, ex);
                mensajeAMostrar = "El Registro no se editó satisfactoriamente";
            }
            System.out.println(alumno.toString());
            if (alumno != null) {
                out.println("<div align='center'>");
                out.println(mensajeAMostrar + "<br/><br/>");
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
            //processRequest(request, response);
            System.out.println("ALOH");
            processPostRequest(request, response);
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
