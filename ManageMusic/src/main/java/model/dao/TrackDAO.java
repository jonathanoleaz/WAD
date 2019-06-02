package model.dao;

import java.util.ArrayList;
import java.util.List;
import model.dto.TrackDTO;
import model.entities.Track;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author jonat
 */
public class TrackDAO {

    static Session sessionObj;
    static SessionFactory sessionFactoryObj;

    // This Method Is Used To Create The Hibernate's SessionFactory Object
    /*private SessionFactory buildSessionFactory() {
     // Creating Configuration Instance & Passing Hibernate Configuration File
     Configuration configObj = new Configuration();
     configObj.configure("hibernate.cfg.xml");

     // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
     ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();

     // Creating Hibernate SessionFactory Instance
     sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
        
     return sessionFactoryObj;
     }*/
    // Method 1: This Method Used To Create A New Track Record In The Database Table
    public void createRecord(TrackDTO artdto) {
        int count = 0;

        try {
            // Getting Session Object From SessionFactory
            //HibernateUtil.openSessionAndBindToThread();
            if(NewHibernateUtil.getSessionFactory().getCurrentSession().isOpen())
                sessionObj=NewHibernateUtil.getSessionFactory().getCurrentSession();
            else
                sessionObj=NewHibernateUtil.getSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            sessionObj.save(artdto.getEntidad());

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();

            System.out.println("\nSuccessfully Created '" + count + "' Records In The Database!\n");
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
            if (null != sessionObj.getTransaction()) {

                System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
                // HibernateUtil.getSessionFactory().close();
            }
        }
    }

    // Method 2: This Method Is Used To Display The Records From The Database Table
    public ArrayList<TrackDTO> displayRecords() {
        List studentsList = new ArrayList();
        ArrayList<TrackDTO> studentsListDTO = new ArrayList();

        try {
            // Getting Session Object From SessionFactory

            //HibernateUtil.openSessionAndBindToThread();
            if(NewHibernateUtil.getSessionFactory().getCurrentSession().isOpen())
                sessionObj=NewHibernateUtil.getSessionFactory().getCurrentSession();
            else
                sessionObj=NewHibernateUtil.getSessionFactory().openSession();

            sessionObj.beginTransaction();

            studentsList = sessionObj.createQuery("FROM Track").list();

            for (Object studentsList1 : studentsList) {
                studentsListDTO.add(new TrackDTO((Track) studentsList1));
            }

        } catch (Exception sqlException) {
            sqlException.printStackTrace();
            if (null != sessionObj.getTransaction()) {

                System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
        } finally {
            if (sessionObj != null) {

                //HibernateUtil.closeSessionAndUnbindFromThread();
                sessionObj.close();
                //sessionFactory.close(); // Getting Transaction Object From Session Object
                System.out.println("SESIÓN CERRADA");
            }
        }
        return studentsListDTO;
    }

    // Method 3: This Method Is Used To Update A Record In The Database Table   
    public void updateRecord(TrackDTO artdto) {

        try {
            // Getting Session Object From SessionFactory
            //HibernateUtil.openSessionAndBindToThread();
            if(NewHibernateUtil.getSessionFactory().getCurrentSession().isOpen())
                sessionObj=NewHibernateUtil.getSessionFactory().getCurrentSession();
            else
                sessionObj=NewHibernateUtil.getSessionFactory().openSession();

            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            // Creating Transaction Entity
            sessionObj.update(artdto.getEntidad());

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            System.out.println("\nTrack With Id?= " + artdto.getEntidad().toString() + " Is Successfully Updated In The Database!\n");
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
            if (null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
                //HibernateUtil.closeSessionAndUnbindFromThread();
            }
        }
    }

    // Method 4(a): This Method Is Used To Delete A Particular Record From The Database Table
    public void deleteRecord(TrackDTO artdto) {
        try {
            // Getting Session Object From SessionFactory
            //HibernateUtil.openSessionAndBindToThread();
            if(NewHibernateUtil.getSessionFactory().getCurrentSession().isOpen())
                sessionObj=NewHibernateUtil.getSessionFactory().getCurrentSession();
            else
                sessionObj=NewHibernateUtil.getSessionFactory().openSession();

            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            // Creating Transaction Entity
            sessionObj.delete(artdto.getEntidad());

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            System.out.println("\nTrack With Id?= " + artdto.getEntidad().toString() + " Is Successfully Updated In The Database!\n");
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
            if (null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
                //HibernateUtil.closeSessionAndUnbindFromThread();
            }
        }
    }

    // Method 4(b): This Method To Find Particular Record In The Database Table
    public TrackDTO findRecordById(Integer find_student_id) {
        TrackDTO artDTO = new TrackDTO();
        Track findTrackObj = null;
        try {
            // Getting Session Object From SessionFactory
            //HibernateUtil.openSessionAndBindToThread();
            if(NewHibernateUtil.getSessionFactory().getCurrentSession().isOpen())
                sessionObj=NewHibernateUtil.getSessionFactory().getCurrentSession();
            else
                sessionObj=NewHibernateUtil.getSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            findTrackObj = (Track) sessionObj.get(Track.class, find_student_id);
            artDTO = new TrackDTO(findTrackObj);
            
            
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
            if (null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }

        } finally {
            if (sessionObj != null) {
                sessionObj.close();
                //HibernateUtil.closeSessionAndUnbindFromThread();
            }
        }
        return artDTO;
    }

    // Method 5: This Method Is Used To Delete All Records From The Database Table
    public void deleteAllRecords() {
        try {
            // Getting Session Object From SessionFactory
//            HibernateUtil.openSessionAndBindToThread();
            if(NewHibernateUtil.getSessionFactory().getCurrentSession().isOpen())
                sessionObj=NewHibernateUtil.getSessionFactory().getCurrentSession();
            else
                sessionObj=NewHibernateUtil.getSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            Query queryObj = sessionObj.createQuery("DELETE FROM Track");
            queryObj.executeUpdate();

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            System.out.println("\nSuccessfully Deleted All Records From The Database Table!\n");
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
            if (null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
//                HibernateUtil.closeSessionAndUnbindFromThread();
            }
        }

    }

}
