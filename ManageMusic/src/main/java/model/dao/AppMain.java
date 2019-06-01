/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entities.Artist;

/**
 *
 * @author jonat
 */
public class AppMain {
    
 
    public static void main(String[] args) {
        System.out.println(".......Hibernate Crud Operations Example.......\n");
 
        System.out.println("\n=======CREATE RECORDS=======\n");
        Artist art=new Artist();
        art.setName("Update");
        /*ArtistDAO.createRecord(art);
 
        System.out.println("\n=======READ RECORDS=======\n");
        List viewStudents = ArtistDAO.displayRecords();
        if(viewStudents != null & viewStudents.size() > 0) {
            for(Object studentObj : viewStudents) {
                System.out.println(studentObj.toString());
            }
        }
 
        System.out.println("\n=======UPDATE RECORDS=======\n");
        int updateId = 1;
        art=ArtistDAO.findRecordById(2);
        art.setName("Nuevo");
        ArtistDAO.updateRecord(art);
        System.out.println("\n=======READ RECORDS AFTER UPDATION=======\n");
        List updateStudent = ArtistDAO.displayRecords();
        if(updateStudent != null & updateStudent.size() > 0) {
            for(Object studentObj : updateStudent) {
                System.out.println(studentObj.toString());
                Artist temporal=(Artist)studentObj;
                System.out.println(temporal.getName());
            }
        }
 
        /*System.out.println("\n=======DELETE RECORD=======\n");
        int deleteId = 5;
        ArtistDAO.deleteRecord(deleteId);
        System.out.println("\n=======READ RECORDS AFTER DELETION=======\n");
        List deleteStudentRecord = ArtistDAO.displayRecords();
        for(Object studentObj : deleteStudentRecord) {
            System.out.println(studentObj.toString());
        }
 
        System.out.println("\n=======DELETE ALL RECORDS=======\n");
        ArtistDAO.deleteAllRecords();
        System.out.println("\n=======READ RECORDS AFTER ALL RECORDS DELETION=======");
        List deleteAll = ArtistDAO.displayRecords();
        if(deleteAll.size() == 0) {
            System.out.println("\nNo Records Are Present In The Database Table!\n");
        }       
        System.exit(0);*/
    }
}
