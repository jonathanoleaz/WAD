package DAOs;

import Entities.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author darkdestiny
 */
public class ArticuloDAO {

    public void create(Articulo a) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InventarioJPAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
    }

    public void update(Articulo a) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InventarioJPAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(a);
        em.getTransaction().commit();
    }

    public void delete(Articulo a) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InventarioJPAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        a = em.find(a.getClass(), a.getClaveart());
        em.remove(a);
        em.getTransaction().commit();
    }

    public Articulo find(Articulo a) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InventarioJPAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        a = em.find(a.getClass(), a.getClaveart());
        em.getTransaction().commit();
        return a;
    }

    public List<Articulo> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InventarioJPAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("select a from Articulo as a");
        List<Articulo> resultados = q.getResultList();
        em.getTransaction().commit();
        return resultados;
    }

    public void registrarMovimientoInventario(Movimientoarticulo movaArticulo) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiniInventarioJPAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Articulo articulo = em.find(Articulo.class,
                movaArticulo.getClaveart().getClaveart());

        if (movaArticulo.getTipo().equals("E")
                && articulo.getExistencia() < movaArticulo.getCantidad()) {
            throw new Exception("No hay Existencia Suficiente");
        }
        em.persist(movaArticulo);

        if (movaArticulo.getTipo().equals("E")) {
            articulo.setExistencia(articulo.getExistencia() + movaArticulo.getCantidad());
        } else {
            articulo.setExistencia(articulo.getExistencia() - movaArticulo.getCantidad());
        }
        em.merge(articulo);
        em.getTransaction().commit();
    }

// public static void main(String[] args) {
// ArticuloDAO dao = new ArticuloDAO();
// Articulo articulo = new Articulo();
// //articulo.setClaveart("art04");
//// articulo.setDescripcion("Telefono");
//// articulo.setExistencia(100);
//// articulo.setPrecio(2000);
// articulo.setClaveart("art03");
// dao.delete(articulo);
// //dao.update(articulo);
// //System.out.println(dao.find(articulo));
// //dao.create(articulo);
// //System.out.println(dao.findAll());
// }
}
