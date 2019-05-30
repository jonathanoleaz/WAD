package Servlets;

import Controllers.*;
import com.mycompany.inventariojpa_m.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("InventarioJP");
    private EntityManager em = emf.createEntityManager();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        System.out.println("nada");
        String action = request.getParameter("accion");
        System.out.println(action);
        String id = request.getParameter("id");
        System.out.println("id:"+id);
        
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
    private void listado(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("nada");
            ArticuloJpaController dao = new ArticuloJpaController(emf);
            
            System.out.println("dd:"+Arrays.toString(dao.findArticuloEntities().toArray()));
            
            request.setAttribute("lista", dao.findArticuloEntities());
            RequestDispatcher vista = request.getRequestDispatcher("ArticuloLista.jsp");
            vista.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ArticuloServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        almacenar(request, response);
        RequestDispatcher vista = request.getRequestDispatcher("ArticuloForm.jsp");
        vista.forward(request, response);
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArticuloJpaController dao = new ArticuloJpaController(emf);
        Articulo c = new Articulo();
        String id = request.getParameter("id");
        c.setClaveart(Integer.parseInt(id));
        c = dao.findArticulo(c.getClaveart());
        dao.destroy(c.getClaveart());
        listado(request, response);

    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArticuloJpaController dao = new ArticuloJpaController(emf);
        Articulo c = new Articulo();
        String id = request.getParameter("id");
        System.out.println("id:"+id);
        c.setClaveart(Integer.parseInt(id));
        c = dao.findArticulo(c.getClaveart());
        System.out.println(c.toString());
        request.setAttribute("articulo", c);
        
        //almacenarArticulo(request, response);
        RequestDispatcher vista = request.getRequestDispatcher("ArticuloForm.jsp");
        vista.forward(request, response);

    }

    private void almacenar(HttpServletRequest request, HttpServletResponse response){
        System.out.println("Guardando carrera");
        Articulo c = new Articulo();
        ArticuloJpaController artDao = new ArticuloJpaController(emf);
        
        Categoria cat = new Categoria();
        CategoriaJpaController catDao=new CategoriaJpaController(emf);
        
        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {            
            try {
                System.out.println("se crea");
//                c.setIdcarrera(Integer.parseInt(request.getParameter("txtID")));
                c.setDescripcion(request.getParameter("txtDescripcion"));
                c.setExistencia(Integer.parseInt(request.getParameter("txtExistencia")));
                c.setPrecio(Integer.parseInt(request.getParameter("txtPrecio")));
                
                cat=catDao.findCategoria(Integer.parseInt(request.getParameter("txtCategoria")));
                c.setIdcategoria(cat);
                
                artDao.create(c);
                listado(request, response);
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
                System.out.println("se actualiza");
                
                c.setClaveart(Integer.parseInt(request.getParameter("id")));
                c.setDescripcion(request.getParameter("txtDescripcion"));
                c.setExistencia(Integer.parseInt(request.getParameter("txtExistencia")));
                c.setPrecio(Integer.parseInt(request.getParameter("txtPrecio")));
                artDao.edit(c);
                listado(request, response);
                
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
