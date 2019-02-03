/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carreraServlets;

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
import objectAndDao.*;

@WebServlet(name = "MostrarCarrera", urlPatterns = {"/MostrarCarrera"})
public class MostrarCarrera extends HttpServlet {

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
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\"  />"
                    + "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">");
            out.println("<title>Servlet MostrarCarrera</title>");
            out.println("</head>");
            out.println("<body>");

            String nombreCarrera, descripcionCarrera;
            String mensajeAMostrar = "";
            int idCarrera, duracionCarrera;

            out.println("<h3 align='center'>Lista de Carreras</h3>");
            out.println("<table align='center' border='1' width='60%'");
            out.println("<tr>");
            out.println("<th> Id Carrera </th>");
            out.println("<th> Nombre Carrera </th>");
            out.println("<th> Descripci&oacute;n Carrera </th>");
            out.println("<th> Duraci&oacute;n Carrera </th>");
            out.println("<th> Acciones </th>");
            out.println("</tr>");

            CarreraDAO dao = new CarreraDAO();
            try {
                List<Carrera> lista = dao.readAll();
                for (int i = 0; i < lista.size(); i++) {
                    Carrera listaCarrera = (Carrera) lista.get(i);
                    idCarrera = listaCarrera.getIdCarrera();
                    nombreCarrera = listaCarrera.getNombreCarrera();
                    descripcionCarrera = listaCarrera.getDescripcion();
                    duracionCarrera = listaCarrera.getDuracion();

                    out.println("<tr>");
                    out.println("<td><a href='VerCarrera?id=" + idCarrera + "' >" + idCarrera + "</a></td>");
                    out.println("<td>" + nombreCarrera + "</td>");
                    out.println("<td>" + descripcionCarrera + "</td>");
                    out.println("<td>" + duracionCarrera + "</td>");
                    out.println("<td> <a href='EditarCarrera?id=" + idCarrera + "' > <i style=\"font-size:24px\" class=\"fa\">&#xf044;</i> </a> "
                            + "<a href='EliminarCarrera?id=" + idCarrera + " '> <i style=\"font-size:24px\" class=\"fa\">&#xf014;</i> </a>"
                            + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");

                out.println("<div align='center'>");
                out.println("<a href='GuardarCarrera'> Agregar Carrera </a>");
                out.println("</div>");
            } catch (SQLException e) {
                mensajeAMostrar = "No se pudó mostrar el listado de Carreras" + e.toString();
                out.println("<div align='center'>");
                out.println(mensajeAMostrar + "<br/><br/>");
                out.println("</div>");
                Logger.getLogger(MostrarCarrera.class.getName()).log(Level.SEVERE, null, e);
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
            Logger.getLogger(MostrarCarrera.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MostrarCarrera.class.getName()).log(Level.SEVERE, null, ex);
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
