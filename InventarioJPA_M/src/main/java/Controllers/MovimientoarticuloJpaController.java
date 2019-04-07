/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.inventariojpa_m.Articulo;
import com.mycompany.inventariojpa_m.Movimientoarticulo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jonat
 */
public class MovimientoarticuloJpaController implements Serializable {

    public MovimientoarticuloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Movimientoarticulo movimientoarticulo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulo claveart = movimientoarticulo.getClaveart();
            if (claveart != null) {
                claveart = em.getReference(claveart.getClass(), claveart.getClaveart());
                movimientoarticulo.setClaveart(claveart);
            }
            em.persist(movimientoarticulo);
            if (claveart != null) {
                claveart.getMovimientoarticuloCollection().add(movimientoarticulo);
                claveart = em.merge(claveart);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Movimientoarticulo movimientoarticulo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movimientoarticulo persistentMovimientoarticulo = em.find(Movimientoarticulo.class, movimientoarticulo.getFolio());
            Articulo claveartOld = persistentMovimientoarticulo.getClaveart();
            Articulo claveartNew = movimientoarticulo.getClaveart();
            if (claveartNew != null) {
                claveartNew = em.getReference(claveartNew.getClass(), claveartNew.getClaveart());
                movimientoarticulo.setClaveart(claveartNew);
            }
            movimientoarticulo = em.merge(movimientoarticulo);
            if (claveartOld != null && !claveartOld.equals(claveartNew)) {
                claveartOld.getMovimientoarticuloCollection().remove(movimientoarticulo);
                claveartOld = em.merge(claveartOld);
            }
            if (claveartNew != null && !claveartNew.equals(claveartOld)) {
                claveartNew.getMovimientoarticuloCollection().add(movimientoarticulo);
                claveartNew = em.merge(claveartNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = movimientoarticulo.getFolio();
                if (findMovimientoarticulo(id) == null) {
                    throw new NonexistentEntityException("The movimientoarticulo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movimientoarticulo movimientoarticulo;
            try {
                movimientoarticulo = em.getReference(Movimientoarticulo.class, id);
                movimientoarticulo.getFolio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movimientoarticulo with id " + id + " no longer exists.", enfe);
            }
            Articulo claveart = movimientoarticulo.getClaveart();
            if (claveart != null) {
                claveart.getMovimientoarticuloCollection().remove(movimientoarticulo);
                claveart = em.merge(claveart);
            }
            em.remove(movimientoarticulo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Movimientoarticulo> findMovimientoarticuloEntities() {
        return findMovimientoarticuloEntities(true, -1, -1);
    }

    public List<Movimientoarticulo> findMovimientoarticuloEntities(int maxResults, int firstResult) {
        return findMovimientoarticuloEntities(false, maxResults, firstResult);
    }

    private List<Movimientoarticulo> findMovimientoarticuloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Movimientoarticulo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Movimientoarticulo findMovimientoarticulo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Movimientoarticulo.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovimientoarticuloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Movimientoarticulo> rt = cq.from(Movimientoarticulo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
