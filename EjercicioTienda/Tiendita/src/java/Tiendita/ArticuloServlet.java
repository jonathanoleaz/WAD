package Tiendita;

import DAO.Articulo;
import DAO.ArticuloDAO;
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
public class ArticuloServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        System.out.println("nada");
        String action = request.getParameter("accion");
        System.out.println(action);
        if (action.equals("ListaProductos")) {
            listadoDeArticulos(request, response);
        } else {
            if (action.equals("nuevaArticulo")) {
                agregarArticulo(request, response);
            } else {
                if (action.equals("eliminarArticulo")) {
                    eliminarArticulo(request, response);
                } else {
                    if (action.equals("actualizarArticulo")) {
                        actualizarArticulo(request, response);
                    } else if (action.equals("guardar")) {
                        almacenarArticulo(request, response);
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
            Logger.getLogger(ArticuloServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ArticuloServlet.class.getName()).log(Level.SEVERE, null, ex);
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
    private void listadoDeArticulos(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("nada");
            ArticuloDAO dao = new ArticuloDAO();
            request.setAttribute("listaDeArticulos", dao.readAll());
            RequestDispatcher vista = request.getRequestDispatcher("ListaProductos.jsp");
            vista.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ArticuloServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarArticulo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        almacenarArticulo(request, response);
        RequestDispatcher vista = request.getRequestDispatcher("ArticuloForm.jsp");
        vista.forward(request, response);
    }

    private void eliminarArticulo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArticuloDAO dao = new ArticuloDAO();
        Articulo c = new Articulo();
        String id = request.getParameter("claveArticulo");
        c.setClavearticulo(id);
        c = dao.read(id);
        dao.delete(c);
        listadoDeArticulos(request, response);

    }

    private void actualizarArticulo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArticuloDAO dao = new ArticuloDAO();
        Articulo c = new Articulo();
        String id = request.getParameter("id");
        System.out.println("id:"+id);
        c.setClavearticulo(id);
        c = dao.read(id);
        System.out.println(c.toString());
        request.setAttribute("producto", c);
        
        //almacenarArticulo(request, response);
        RequestDispatcher vista = request.getRequestDispatcher("ProductoForm.jsp");
        vista.forward(request, response);

    }

    private void almacenarArticulo(HttpServletRequest request, HttpServletResponse response){
        System.out.println("Guardando articulo");
        Articulo c = new Articulo();
        ArticuloDAO d = new ArticuloDAO();
        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
            
            try {
                c.setClavearticulo(request.getParameter("txtClaveArticulo"));
                c.setDescripcionproducto(request.getParameter("txtDescripcionProducto"));
                c.setExistencia(Integer.parseInt(request.getParameter("txtExistencia")));
                c.setIdcategoria(Integer.parseInt(request.getParameter("txtCategoria")));
                c.setNombreproducto(request.getParameter("txtNombreProducto"));
                c.setPrecio(Double.parseDouble(request.getParameter("txtPrecio")));
                d.create(c);
                listadoDeArticulos(request, response);
            } catch (Exception ex) {
                try {
                    ex.printStackTrace();
                    response.sendRedirect("Error.html");
                } catch (IOException ex1) {
                    Logger.getLogger(ArticuloServlet.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } else {
            try {
                System.out.println("Id found");
                c.setClavearticulo(request.getParameter("txtClaveArticulo"));
                c.setDescripcionproducto(request.getParameter("txtDescripcionProducto"));
                c.setExistencia(Integer.parseInt(request.getParameter("txtExistencia")));
                c.setIdcategoria(Integer.parseInt(request.getParameter("txtCategoria")));
                c.setNombreproducto(request.getParameter("txtNombreProducto"));
                c.setPrecio(Double.parseDouble(request.getParameter("txtPrecio")));
                d.update(c);
                listadoDeArticulos(request, response);
            } catch (Exception ex) {
                try {
                    ex.printStackTrace();
                    response.sendRedirect("Error.html");
                } catch (IOException ex1) {
                    Logger.getLogger(ArticuloServlet.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }

}
