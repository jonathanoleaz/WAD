package Servlets;

import entities.Categoria;
import entities.NewHibernateUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author jonat
 */
public class CategoriaServlet extends HttpServlet {

    Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        System.out.println("nada");
        String action = request.getParameter("accion");
        System.out.println(action);
        String id = request.getParameter("id");
        System.out.println("id:" + id);

        if (action != null) {
            if (action.equals("lista")) {
                System.out.println("nananana");
                listado(request, response);
            } else {
                if (action.equals("nueva")) {
                    agregar(request, response);
                } else {
                    if (action.equals("eliminar")) {
                        eliminar(request, response);
                    } else {
                        if (action.equals("actualizar")) {
                            actualizar(request, response);
                        } else if (action.equals("guardar")) {
                            almacenar(request, response);
                        }
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

    private void listado(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("nada");

            

            session.createQuery("from Categoria");

            request.setAttribute("lista", session.createQuery("from Categoria").list());
            RequestDispatcher vista = request.getRequestDispatcher("CategoriaLista.jsp");
            vista.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        almacenar(request, response);
        RequestDispatcher vista = request.getRequestDispatcher("CategoriaForm.jsp");
        vista.forward(request, response);
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Categoria c = new Categoria();
        String id = request.getParameter("id");
        c.setIdcategoria(Integer.parseInt(id));
        c = (Categoria) session.get(Categoria.class, c);
        session.delete(c);
        listado(request, response);

    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Categoria c = new Categoria();
        String id = request.getParameter("id");
        System.out.println("id:" + id);
        c.setIdcategoria(Integer.parseInt(id));
        c = (Categoria) session.get(Categoria.class, c);
        System.out.println(c.toString());
        request.setAttribute("articulo", c);

        //almacenarCategoria(request, response);
        RequestDispatcher vista = request.getRequestDispatcher("CategoriaForm.jsp");
        vista.forward(request, response);

    }

    private void almacenar(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Guardando carrera");
        Categoria c = new Categoria();

        Categoria cat = new Categoria();

        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
            try {
                System.out.println("se crea");
//                c.setIdcarrera(Integer.parseInt(request.getParameter("txtID")));
                c.setDescripcion(request.getParameter("txtDescripcion"));
                

                session.save(c);
                listado(request, response);
            } catch (Exception ex) {
                try {
                    ex.printStackTrace();
                    response.sendRedirect("Error.html");
                } catch (IOException ex1) {
                    Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } else {
            try {
                System.out.println("se actualiza");

                c.setIdcategoria(Integer.parseInt(request.getParameter("id")));
                c.setDescripcion(request.getParameter("txtDescripcion"));
                session.save(c);
                listado(request, response);

            } catch (Exception ex) {
                try {
                    ex.printStackTrace();
                    response.sendRedirect("Error.html");
                } catch (IOException ex1) {
                    Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }
}
