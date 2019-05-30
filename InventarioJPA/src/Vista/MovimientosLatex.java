/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import DAOs.ArticuloJpaController;
import DAOs.MovimientoarticuloJpaController;
import DAOs.exceptions.IllegalOrphanException;
import DAOs.exceptions.NonexistentEntityException;
import Entities.Articulo;
import Entities.Movimientoarticulo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author jonat
 */
public class Movimientos extends javax.swing.JFrame {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("InventarioJPAPU");
    private EntityManager em = emf.createEntityManager();

    /**
     * Creates new form Inventario
     */
    public Movimientos() {
        initComponents();
        
        em.getTransaction().begin();
        em.getTransaction().commit();
        ArticuloJpaController artContr = new ArticuloJpaController(emf);
        
        List<Articulo> cars = artContr.findArticuloEntities();
        String[] array = new String[cars.size()];

        for (int i = 0; i < array.length; i++) {
            array[i] = cars.get(i).getClaveart()+ "";
        }

        jcmbArticulo.setModel(new DefaultComboBoxModel(array));              

    }


    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:

        Movimientoarticulo movtoArt=new Movimientoarticulo();
        MovimientoarticuloJpaController movtoContr=new MovimientoarticuloJpaController(emf);
        
        ArticuloJpaController artContr = new ArticuloJpaController(emf);
        Articulo art = new Articulo();
        
        movtoArt.setCantidad((Integer) this.jspnCantidad.getValue());
        System.out.println(this.jcmbArticulo.getSelectedItem());
        Articulo articuloSeleccionado=artContr.findArticulo(Integer.parseInt(this.jcmbArticulo.getSelectedItem().toString()));
        
        movtoArt.setClaveart(articuloSeleccionado);
        movtoArt.setFecha(jdtFecha.getDate().toString());
        movtoArt.setTipo(this.jcmbTipo.getSelectedItem().toString());                
        
        
        movtoContr.create(movtoArt);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            // TODO add your handling code here:

            ArticuloJpaController artContr = new ArticuloJpaController(emf);
            Articulo art = new Articulo();
            art.setClaveart((Integer) this.jspnClave.getValue());
            artContr.destroy(art.getClaveart());
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(Movimientos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Movimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Movimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Movimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Movimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Movimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Movimientos().setVisible(true);
            }
        });
    }

    public void showDataInTable(List<Articulo> listEmployees, JTable table) {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Clave", "Descripcion", "Precio", "Existencia"}, 0);
        for (Articulo alum : listEmployees) {
            model.addRow(new Object[]{alum.getClaveart(), alum.getDescripcion(), alum.getPrecio(), alum.getExistencia()});
        }
        table.setModel(model);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private org.jdatepicker.util.JDatePickerUtil jDatePickerUtil1;
    private org.jdatepicker.util.JDatePickerUtil jDatePickerUtil2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JComboBox<String> jcmbArticulo;
    private javax.swing.JComboBox<String> jcmbTipo;
    private com.toedter.calendar.JDateChooser jdtFecha;
    private javax.swing.JSpinner jspnCantidad;
    private javax.swing.JSpinner jspnClave;
    // End of variables declaration//GEN-END:variables
}
