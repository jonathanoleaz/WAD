/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.reports;

import dao.Conexion;
import java.io.File;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jonat
 */
public class Reporter {

    //public static void main(String[] args) throws JRException {
       // generarPDF(1);
    //}
    
    

    public Reporter() {
    }

    public File generarPDF(int idAsistente) throws JRException {
        Map parameters = new HashMap();
        parameters.put("idAsistente", idAsistente);

        Connection conn = Conexion.getConexion();
        
        //System.out.println("ruta: "+this.getClass().getProtectionDomain().getCodeSource().getLocation());

        //System.out.println(System.getProperty("user.dir"));

        // Java 7
        //System.out.println(Paths.get("").toAbsolutePath().toString());
        System.out.println("Valor: "+parameters.get("idAsistente"));

        JasperPrint jasperPrint = JasperFillManager.fillReport("./ReporteRegistradoHor.jasper", parameters, conn);
        JRPdfExporter exp = new JRPdfExporter();
        exp.setExporterInput(new SimpleExporterInput(jasperPrint));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("./ejemplo.pdf"));
        SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
        exp.setConfiguration(conf);
        exp.exportReport();

        File f = new File("./ejemplo.pdf");
        //JasperViewer.viewReport(jasperPrint, false);
        System.out.println("Fin");
        
        return f;
    }
}
