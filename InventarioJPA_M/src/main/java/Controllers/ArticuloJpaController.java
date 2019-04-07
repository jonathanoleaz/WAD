/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import com.mycompany.inventariojpa_m.Articulo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.inventariojpa_m.Categoria;
import com.mycompany.inventariojpa_m.Movimientoarticulo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jonat
 */
public class ArticuloJpaController implements Serializable {

    public ArticuloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Articulo articulo) {
        if (articulo.getMovimientoarticuloCollection() == null) {
            articulo.setMovimientoarticuloCollection(new ArrayList<Movimientoarticulo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria idcategoria = articulo.getIdcategoria();
            if (idcategoria != null) {
                idcategoria = em.getReference(idcategoria.getClass(), idcategoria.getIdcategoria());
                articulo.setIdcategoria(idcategoria);
            }
            Collection<Movimientoarticulo> attachedMovimientoarticuloCollection = new ArrayList<Movimientoarticulo>();
            for (Movimientoarticulo movimientoarticuloCollectionMovimientoarticuloToAttach : articulo.getMovimientoarticuloCollection()) {
                movimientoarticuloCollectionMovimientoarticuloToAttach = em.getReference(movimientoarticuloCollectionMovimientoarticuloToAttach.getClass(), movimientoarticuloCollectionMovimientoarticuloToAttach.getFolio());
                attachedMovimientoarticuloCollection.add(movimientoarticuloCollectionMovimientoarticuloToAttach);
            }
            articulo.setMovimientoarticuloCollection(attachedMovimientoarticuloCollection);
            em.persist(articulo);
            if (idcategoria != null) {
                idcategoria.getArticuloCollection().add(articulo);
                idcategoria = em.merge(idcategoria);
            }
            for (Movimientoarticulo movimientoarticuloCollectionMovimientoarticulo : articulo.getMovimientoarticuloCollection()) {
                Articulo oldClaveartOfMovimientoarticuloCollectionMovimientoarticulo = movimientoarticuloCollectionMovimientoarticulo.getClaveart();
                movimientoarticuloCollectionMovimientoarticulo.setClaveart(articulo);
                movimientoarticuloCollectionMovimientoarticulo = em.merge(movimientoarticuloCollectionMovimientoarticulo);
                if (oldClaveartOfMovimientoarticuloCollectionMovimientoarticulo != null) {
                    oldClaveartOfMovimientoarticuloCollectionMovimientoarticulo.getMovimientoarticuloCollection().remove(movimientoarticuloCollectionMovimientoarticulo);
                    oldClaveartOfMovimientoarticuloCollectionMovimientoarticulo = em.merge(oldClaveartOfMovimientoarticuloCollectionMovimientoarticulo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Articulo articulo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulo persistentArticulo = em.find(Articulo.class, articulo.getClaveart());
            Categoria idcategoriaOld = persistentArticulo.getIdcategoria();
            Categoria idcategoriaNew = articulo.getIdcategoria();
            Collection<Movimientoarticulo> movimientoarticuloCollectionOld = persistentArticulo.getMovimientoarticuloCollection();
            Collection<Movimientoarticulo> movimientoarticuloCollectionNew = articulo.getMovimientoarticuloCollection();
            List<String> illegalOrphanMessages = null;
            for (Movimientoarticulo movimientoarticuloCollectionOldMovimientoarticulo : movimientoarticuloCollectionOld) {
                if (!movimientoarticuloCollectionNew.contains(movimientoarticuloCollectionOldMovimientoarticulo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Movimientoarticulo " + movimientoarticuloCollectionOldMovimientoarticulo + " since its claveart field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idcategoriaNew != null) {
                idcategoriaNew = em.getReference(idcategoriaNew.getClass(), idcategoriaNew.getIdcategoria());
                articulo.setIdcategoria(idcategoriaNew);
            }
            Collection<Movimientoarticulo> attachedMovimientoarticuloCollectionNew = new ArrayList<Movimientoarticulo>();
            for (Movimientoarticulo movimientoarticuloCollectionNewMovimientoarticuloToAttach : movimientoarticuloCollectionNew) {
                movimientoarticuloCollectionNewMovimientoarticuloToAttach = em.getReference(movimientoarticuloCollectionNewMovimientoarticuloToAttach.getClass(), movimientoarticuloCollectionNewMovimientoarticuloToAttach.getFolio());
                attachedMovimientoarticuloCollectionNew.add(movimientoarticuloCollectionNewMovimientoarticuloToAttach);
            }
            movimientoarticuloCollectionNew = attachedMovimientoarticuloCollectionNew;
            articulo.setMovimientoarticuloCollection(movimientoarticuloCollectionNew);
            articulo = em.merge(articulo);
            if (idcategoriaOld != null && !idcategoriaOld.equals(idcategoriaNew)) {
                idcategoriaOld.getArticuloCollection().remove(articulo);
                idcategoriaOld = em.merge(idcategoriaOld);
            }
            if (idcategoriaNew != null && !idcategoriaNew.equals(idcategoriaOld)) {
                idcategoriaNew.getArticuloCollection().add(articulo);
                idcategoriaNew = em.merge(idcategoriaNew);
            }
            for (Movimientoarticulo movimientoarticuloCollectionNewMovimientoarticulo : movimientoarticuloCollectionNew) {
                if (!movimientoarticuloCollectionOld.contains(movimientoarticuloCollectionNewMovimientoarticulo)) {
                    Articulo oldClaveartOfMovimientoarticuloCollectionNewMovimientoarticulo = movimientoarticuloCollectionNewMovimientoarticulo.getClaveart();
                    movimientoarticuloCollectionNewMovimientoarticulo.setClaveart(articulo);
                    movimientoarticuloCollectionNewMovimientoarticulo = em.merge(movimientoarticuloCollectionNewMovimientoarticulo);
                    if (oldClaveartOfMovimientoarticuloCollectionNewMovimientoarticulo != null && !oldClaveartOfMovimientoarticuloCollectionNewMovimientoarticulo.equals(articulo)) {
                        oldClaveartOfMovimientoarticuloCollectionNewMovimientoarticulo.getMovimientoarticuloCollection().remove(movimientoarticuloCollectionNewMovimientoarticulo);
                        oldClaveartOfMovimientoarticuloCollectionNewMovimientoarticulo = em.merge(oldClaveartOfMovimientoarticuloCollectionNewMovimientoarticulo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = articulo.getClaveart();
                if (findArticulo(id) == null) {
                    throw new NonexistentEntityException("The articulo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulo articulo;
            try {
                articulo = em.getReference(Articulo.class, id);
                articulo.getClaveart();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The articulo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Movimientoarticulo> movimientoarticuloCollectionOrphanCheck = articulo.getMovimientoarticuloCollection();
            for (Movimientoarticulo movimientoarticuloCollectionOrphanCheckMovimientoarticulo : movimientoarticuloCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Articulo (" + articulo + ") cannot be destroyed since the Movimientoarticulo " + movimientoarticuloCollectionOrphanCheckMovimientoarticulo + " in its movimientoarticuloCollection field has a non-nullable claveart field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Categoria idcategoria = articulo.getIdcategoria();
            if (idcategoria != null) {
                idcategoria.getArticuloCollection().remove(articulo);
                idcategoria = em.merge(idcategoria);
            }
            em.remove(articulo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Articulo> findArticuloEntities() {
        return findArticuloEntities(true, -1, -1);
    }

    public List<Articulo> findArticuloEntities(int maxResults, int firstResult) {
        return findArticuloEntities(false, maxResults, firstResult);
    }

    private List<Articulo> findArticuloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Articulo.class));
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

    public Articulo findArticulo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Articulo.class, id);
        } finally {
            em.close();
        }
    }

    public int getArticuloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Articulo> rt = cq.from(Articulo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
