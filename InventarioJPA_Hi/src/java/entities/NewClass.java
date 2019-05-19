/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author jonat
 */
public class NewClass {
    
    public static void main(String[] args) {
        System.out.println(getAllNotes().toString());
        
    }

    public static List getAllNotes()
 {
     Session session = null;
     session = NewHibernateUtil.getSessionFactory().getCurrentSession();
     List<Articulo> notelist = null;
     Transaction tx = session.beginTransaction();
     Query query = session.createQuery("from Articulo");
     
     notelist = query.list();
     return notelist;
 }
}
