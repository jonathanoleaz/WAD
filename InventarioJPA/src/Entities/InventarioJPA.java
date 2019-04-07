/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import DAOs.*;
import java.util.Arrays;

/**
 *
 * @author jonat
 */
public class InventarioJPA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InventarioJPAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
        
        
        
        TypedQuery<Articulo> q = em.createNamedQuery("Articulo.findAll", Articulo.class);
        List<Articulo> listaAlumnos = q.getResultList();
        System.out.println(listaAlumnos);
        em.close();
        
        
        ArticuloJpaController artContr=new ArticuloJpaController(emf);
        artContr.create(new Articulo(1, "Cocina", 12));
        
        System.out.println(Arrays.toString(artContr.findArticuloEntities().toArray()));
        // TODO code application logic here
    }
    
}
