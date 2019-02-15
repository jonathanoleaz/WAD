/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logIn;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objectAndDao.Usuario;
import objectAndDao.UsuarioDAO;

/**
 *
 * @author jonat
 */
public class iniciarSesionConJavaScript extends HttpServlet {

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

        
    }

    protected void processPostRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        System.out.println("frio");
        /* TODO output your page here. You may use following sample code. */
        Usuario user = new Usuario();
        UsuarioDAO userDao = new UsuarioDAO();
        String mensajeMostrar = "Sesion NO iniciada";

        user = userDao.readByNombre(request.getParameter("nombre"));
        if (user.getNombreUsuario() == null) {
            System.out.println("ddddd");
            response.sendRedirect("iniciarSesion");
            return;
        }
        Boolean coincidencia;
        coincidencia = UsuarioDAO.checkPass(request.getParameter("password"), user.getPassword());
        System.out.println(">>" + coincidencia);
        if (coincidencia) {

            mensajeMostrar = "Sesión iniciada";
            session.setAttribute("nombreUsuario", user.getNombreUsuario());
            session.setAttribute("idUsuario", user.getIdusuario());
            response.sendRedirect("MostrarAlumno");
        } else {
            response.sendRedirect("iniciarSesion");

        }

        /*if (session.isNew()) {
                title = "Welcome to my website";
                session.setAttribute(userIDKey, userID);
            } else {
                visitCount = (Integer) session.getAttribute(visitCountKey);
                visitCount = visitCount + 1;
                userID = (String) session.getAttribute(userIDKey);
            }
            session.setAttribute(visitCountKey, visitCount);*/
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
            Logger.getLogger(iniciarSesionConJavaScript.class.getName()).log(Level.SEVERE, null, ex);
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
