/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carreraServlets;

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
@WebServlet(name = "GuardarCarrera", urlPatterns = {"/GuardarCarrera"})
public class GuardarCarrera extends HttpServlet {

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
            out.println("<head>");
            out.println("<title>Servlet GuardarCarrera</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h3 align='center'>Datos de la Carrera</h3>");

            CarreraDAO dao = new CarreraDAO();
            Carrera carrera = new Carrera();
            //carrera.setIdCarrera(Integer.parseInt(request.getParameter("id")));
            /*try {
              //  carrera = dao.read(carrera.getIdCarrera());
            } catch (SQLException ex) {
                //Logger.getLogger(VerCarrera.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            if (carrera != null) {
                out.println("<form action=\"GuardarCarrera\" method=\"post\">"
                        //+ "ID:                  <input type=\"number\" name=\"id\" value=\"" + carrera.getIdCarrera() + "\" > "
                        + "<br>Nombre carrera:  <input type=\"text\" name=\"nombre\" value=nombre > "
                        + "<br>Descripción carrera: <input type=\"text\" size=\"35\" name=\"descripcion\" value=descripción>"
                        + "<br>Duración:            <input type=\"number\" name=\"duracion\" value=duracion>"
                        + "<br><input type=\"submit\" value=\"Guardar\">"
                        + "</form>");
            }
            System.out.println(carrera.getDescripcion());
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
            out.println("<head>");
            out.println("<title>Servlet GuardarCarrera</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h3 align='center'>Guardado de carrera</h3>");

            CarreraDAO dao = new CarreraDAO();
            String mensajeAMostrar = "";
            Carrera carrera = new Carrera();
            System.out.println(java.util.Arrays.asList(request.getParameterNames()));
            System.out.println(request.getParameterNames());
            //carrera.setIdCarrera(Integer.parseInt(request.getParameter("id")));
            carrera.setNombrecarrera(request.getParameter("nombre"));
            carrera.setDescripcion(request.getParameter("descripcion"));
            carrera.setDuracion(Integer.parseInt(request.getParameter("duracion")));
            try {
                dao.create(carrera);
                mensajeAMostrar = "El Registro se guardó satisfactoriamente";
            } catch (SQLException ex) {
                Logger.getLogger(VerCarrera.class.getName()).log(Level.SEVERE, null, ex);
                mensajeAMostrar = "El Registro no se guardó satisfactoriamente";
            }
            if (carrera != null) {
                out.println("<div align='center'>");
                out.println(mensajeAMostrar + "<br/><br/>");
                out.println("<a href='MostrarCarrera'> Lista de Carreras </a>");
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
            Logger.getLogger(VerCarrera.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(VerCarrera.class.getName()).log(Level.SEVERE, null, ex);
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
