/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.File;

public class Prueba {
    public static void main(String[] args) {
        File miDir = new File (".");
        try {
            Email email = new Email();
            //email.sendEmailMultimedia("luchofig28@gmail.com", "Asunto", "Texto del correo",miDir.getCanonicalPath()+"\\pdf\\imagen.jpg","TítuloImagen.jpg",
              //      miDir.getCanonicalPath()+"\\pdf\\JavaServlet.pdf","TítulPDF.pdf");
            
            email.sendEmailText("oleazunigajonathan@gmail.com", "Registro", "Se ha registrado");
        }
        catch(Exception e) {
            e.printStackTrace();
       }
    }
}

