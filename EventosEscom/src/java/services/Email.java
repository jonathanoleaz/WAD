/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {
    private final Properties properties = new Properties();
    private static final String PASSWORD="Sony4660";
    private static final String EMAIL="oleazunigajonathan@gmail.com";
    private Session session;
    
    private void configurarEmail() {
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.mail.sender", EMAIL);
        properties.put("mail.smtp.user", EMAIL);
        properties.put("mail.smtp.auth", "true");
        session = Session.getDefaultInstance(properties);
    }
    
    public void sendEmailText(String destino, String asunto, String texto){
	configurarEmail();
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
            message.setSubject(asunto);
            message.setText(texto);
            Transport t = session.getTransport("smtp");
            t.connect((String) properties.get("mail.smtp.user"), PASSWORD);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            System.out.println("Se mando el correo de manera exitosa.");
        } catch (MessagingException me) {
            me.printStackTrace();
            System.out.println("No se pudo enviar el correo. Ocurrió un problema.");
        }
		
    }
    public void sendEmailMultimedia(String destino, String asunto, String textoEnviar, String imagenEnviar, String tituloImagen, String pdfEnviar, String tituloPdf){
	configurarEmail();
        try {
            //Texto
            BodyPart texto = new MimeBodyPart();
            texto.setText(textoEnviar);
            
            //Imagen
            BodyPart imagen = new MimeBodyPart();
            imagen.setDataHandler(new DataHandler(new FileDataSource(imagenEnviar)));
            imagen.setFileName(tituloImagen);
            
            //Pdf
            BodyPart pdf = new MimeBodyPart();
            pdf.setDataHandler(new DataHandler(new FileDataSource(pdfEnviar)));
            pdf.setFileName(tituloPdf);
            
            MimeMultipart contenido = new MimeMultipart();
            contenido.addBodyPart(texto);
            contenido.addBodyPart(imagen);
            contenido.addBodyPart(pdf);
               
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
            message.setSubject(asunto);
            message.setContent(contenido);
            
            Transport t = session.getTransport("smtp");
            t.connect((String) properties.get("mail.smtp.user"), PASSWORD);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            System.out.println("Se mando el correo de manera exitosa.");
        } catch (MessagingException me) {
            me.printStackTrace();
            System.out.println("No se pudo enviar el correo. Ocurrió un problema.");
        }
		
    }
}
