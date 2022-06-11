/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Ventana_E extends javax.swing.JFrame {

    /**
     * Creates new form Ventana_E
     */
    Vector<String> hora = new Vector<String>();
    Vector<String> calle1 = new Vector<String>();
    Vector<String> calle2 = new Vector<String>();
    Vector<String> delegacion = new Vector<String>();
    Vector<String> delito = new Vector<String>();

    Vector<Vector<String>> cuadran = new Vector<Vector<String>>();
    String cuad="";
    public Ventana_E(Vector<Vector<String>> cuadran) {
        initComponents();
        setLocationRelativeTo(this);
        limpiar();
        hora = cuadran.get(0);
        calle1 = cuadran.get(1);
        calle2 = cuadran.get(2);
        delegacion = cuadran.get(3);
        delito = cuadran.get(4);
        this.setTitle("DATOS RECUPERADOS DEL SERVIDOR RMI");
    }
    public void limpiar(){
        hora.clear();
        calle1.clear();
        calle2.clear();
        delegacion.clear();
        delito.clear();
        cuadran.clear();
    }

    public void visualizar() {
        DefaultTableModel modelo = (DefaultTableModel) Tabla_E.getModel();
        if (!hora.isEmpty()) {
            info.setText(hora.size() + " registros recuperados del cuadrante: "+cuadrante_e());
            for (int i = 0; i < hora.size(); i++) {
                modelo.addRow(new String[]{hora.get(i), calle1.get(i), calle2.get(i), delegacion.get(i), delito.get(i)});
            }
            setVisible(true);
        } else {
            info.setText("No hay datos");
            JOptionPane.showMessageDialog(null, "No se encontraron datos", "Información", 2);
        }
        
        hora.clear();
        calle1.clear();
        calle2.clear();
        delegacion.clear();
        delito.clear();
    }

    public void cuadrante_r(String cuad){
        this.cuad = cuad;        
    }
    
    public String cuadrante_e(){
        return cuad;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_E = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Tabla_E.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "HORA", "CALLE 1", "CALLE 2", "DELEGACION", "DELITO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Tabla_E);

        info.setForeground(new java.awt.Color(255, 51, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
                    .addComponent(info, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(info, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Ventana_E.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana_E.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana_E.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana_E.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // new Ventana_E().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla_E;
    public static final javax.swing.JLabel info = new javax.swing.JLabel();
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
