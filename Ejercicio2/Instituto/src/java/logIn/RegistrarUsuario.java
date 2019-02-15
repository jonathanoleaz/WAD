/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logIn;

import alumnoServlets.VerAlumno;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objectAndDao.Alumno;
import objectAndDao.AlumnoDAO;
import objectAndDao.Carrera;
import objectAndDao.CarreraDAO;
import objectAndDao.Usuario;
import objectAndDao.UsuarioDAO;

/**
 *
 * @author jonat
 */
public class RegistrarUsuario extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\"  />");
            out.println("<title>Servlet RegistrarUsuario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistrarUsuario at " + request.getContextPath() + "</h1>");
            out.println("<form action=\"RegistrarUsuario\" method=\"post\" accept-charset=\"ISO-8859-1\">"
                    + "<br>Nombre de usuario:   <input type=\"text\"   name=\"nombre\"      > "
                    + "<br>Contraseña:          <input type=\"text\"   name=\"password\"     > "
                    + "<br>Confirme contraseña: <input type=\"text\"   name=\"confirmpassword\"     > "
                    + "<br>Tipo de usuario: "
                    + "<select name=\"carrera\">\n");
            out.println("<option value=1\"1(root)</option>\n");
            out.println("<option value=2\"2</option>\n");
            out.println("<option value=3\"3</option>\n");
            out.print("</select>"
                    + "<br><input type=\"submit\" value=\"Registrar usuario\">"
                    + "</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void processPostRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");

        UsuarioDAO user_dao = new UsuarioDAO();
        Usuario user = null;

        user = user_dao.readByNombre(request.getParameter("nombre"));
        System.out.println(user.toString());
        if (user.getNombreUsuario() != null) { //ya existe un usuario con el mismo nombre
            response.sendRedirect("newUser.html");
            return;
        }
        user.setNombreUsuario(request.getParameter("nombre"));
        user.setPassword(UsuarioDAO.hashPassword(request.getParameter("password")));
        user.setTipoUsuario(Integer.parseInt(request.getParameter("carrera")));
        System.out.println(user.toString());
        user_dao.create(user);
        response.sendRedirect("login.html");
        
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
        processRequest(request, response);
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
            processPostRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(RegistrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
