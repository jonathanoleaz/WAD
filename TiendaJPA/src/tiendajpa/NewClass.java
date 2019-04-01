/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendajpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author jonat
 */
public class NewClass {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TiendaJPAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
        
        TypedQuery<Articulo> q = em.createNamedQuery("Articulo.findAll", Articulo.class);
        List<Articulo> listaAlumnos = q.getResultList();
        System.out.println(listaAlumnos);
        em.close();
    }
    
}
