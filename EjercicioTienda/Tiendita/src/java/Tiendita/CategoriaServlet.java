package Tiendita;

import DAO.Categoria;
import DAO.CategoriaDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonat
 */
public class CategoriaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String action = request.getParameter("accion");
        System.out.println(action);
        if (action.equals("ListaCategorias")) {
            listadoDeCategorias(request, response);
        } else {
            if (action.equals("nuevaCategoria")) {
                agregarCategoria(request, response);
            } else {
                if (action.equals("eliminarCategoria")) {
                    eliminarCategoria(request, response);
                } else {
                    if (action.equals("actualizarCategoria")) {
                        actualizarCategoria(request, response);
                    } else if (action.equals("guardar")) {
                        almacenarCategoria(request, response);
                    }
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
/**/
    private void listadoDeCategorias(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("nada");
            CategoriaDAO dao = new CategoriaDAO();
            request.setAttribute("listaDeCategorias", dao.readAll());
            RequestDispatcher vista = request.getRequestDispatcher("ListaCategorias.jsp");
            vista.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        almacenarCategoria(request, response);
        RequestDispatcher vista = request.getRequestDispatcher("CategoriaForm.jsp");
        vista.forward(request, response);
    }

    private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CategoriaDAO dao = new CategoriaDAO();
        Categoria c = new Categoria();
        int id = Integer.parseInt(request.getParameter("id"));
        c.setIdcategoria(id);
        c = dao.read(c.getIdcategoria());
        dao.delete(c);
        listadoDeCategorias(request, response);

    }

    private void actualizarCategoria(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CategoriaDAO dao = new CategoriaDAO();
        Categoria c = new Categoria();
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("id:"+id);
        c.setIdcategoria(id);
        c = dao.read(id);
        System.out.println(c.toString());
        request.setAttribute("categoria", c);
        
        //almacenarCategoria(request, response);
        RequestDispatcher vista = request.getRequestDispatcher("CategoriaForm.jsp");
        vista.forward(request, response);

    }

    private void almacenarCategoria(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Categoria c = new Categoria();
        CategoriaDAO d = new CategoriaDAO();
        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
            System.out.println(request.getParameter("txtNombreCategoria"));
            c.setNombreCategoria(request.getParameter("txtNombreCategoria"));
            c.setDescripcionCategoria(request.getParameter("txtDescripcion"));
            d.create(c);
            listadoDeCategorias(request, response);
        } else {
            System.out.println("Id found");
            c.setIdcategoria(Integer.parseInt(request.getParameter("id")));
            c.setNombreCategoria(request.getParameter("txtNombreCategoria"));
            c.setDescripcionCategoria(request.getParameter("txtDescripcion"));
            d.update(c);
            listadoDeCategorias(request, response);
        }
    }

}
