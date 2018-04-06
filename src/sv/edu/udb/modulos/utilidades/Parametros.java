/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.modulos.utilidades;

import java.math.BigDecimal;
import javax.swing.JOptionPane;
import sv.edu.udb.Services.AdminService;

/**
 *
 * @author yuuta
 */
public class Parametros extends javax.swing.JInternalFrame {

    /**
     * Creates new form Parametros
     */
    public static int bandera = 0;
    AdminService adminService = new AdminService();
    sv.edu.udb.Data.modelos.Parametros parametros;
    public Parametros() {
        initComponents();
        verificarExistenciaPatametros();
    }
    
    private void llenarCampos(){        
        parametros = adminService.getParametros();
        txtCantidadPalum.setText(String.valueOf(parametros.cantidad_prestar_alumno));
        txtCantidadPprof.setText(String.valueOf(parametros.cantidad_prestar_profesor));
        txtDiasPrestamoalum.setText(String.valueOf(parametros.dias_prestar_alumno));
        txtDiasPrestamoprof.setText(String.valueOf(parametros.dias_prestar_profesor));
        txtMoraDia.setText(String.valueOf(parametros.mora_por_dia));
    }
    
    private void verificarExistenciaPatametros(){
        parametros = adminService.getParametros();
        if(parametros.getId_parametros()==0){
            btnAccion.setText("Ingresar");
        }
        else{
            llenarCampos();
            btnAccion.setText("Modificar");
        }
    }
    
    private sv.edu.udb.Data.modelos.Parametros leerParametros(){
        sv.edu.udb.Data.modelos.Parametros parametros = new sv.edu.udb.Data.modelos.Parametros();
        
        parametros.id_parametros=this.parametros.id_parametros;
        parametros.dias_prestar_alumno=Integer.valueOf(txtDiasPrestamoalum.getText());
        parametros.dias_prestar_profesor=Integer.valueOf(txtDiasPrestamoprof.getText());
        parametros.cantidad_prestar_alumno=Integer.valueOf(txtCantidadPalum.getText());
        parametros.cantidad_prestar_profesor=Integer.valueOf(txtCantidadPprof.getText());
        parametros.mora_por_dia=new BigDecimal(txtMoraDia.getText());
        
        return parametros;
    }
    
    private void limpiarTxt(){
        txtCantidadPalum.setText("");
        txtCantidadPprof.setText("");
        txtDiasPrestamoalum.setText("");
        txtDiasPrestamoprof.setText("");
        txtMoraDia.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtDiasPrestamoalum = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDiasPrestamoprof = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCantidadPalum = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCantidadPprof = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMoraDia = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnAccion = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Parametros");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Configuración", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel1.setText("Dias Prestamo Alumno");

        jLabel2.setText("Dias Prestamo Profesor");

        jLabel3.setText("Cantidad Prestamo Alumno");

        jLabel4.setText("Cantidad Prestamo Profesor");

        jLabel5.setText("Mora por dia");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(77, 77, 77)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiasPrestamoalum, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(txtDiasPrestamoprof)
                    .addComponent(txtCantidadPalum)
                    .addComponent(txtCantidadPprof)
                    .addComponent(txtMoraDia))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiasPrestamoalum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDiasPrestamoprof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCantidadPalum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCantidadPprof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMoraDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAccion.setText("Modificar");
        btnAccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccionActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(btnAccion)
                .addGap(70, 70, 70)
                .addComponent(btnLimpiar)
                .addContainerGap(118, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAccion)
                    .addComponent(btnLimpiar))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        bandera = 0;
    }//GEN-LAST:event_formInternalFrameClosing

    private void btnAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccionActionPerformed
        try {
            if (btnAccion.getText().equals("Ingresar")) {
                adminService.insertarParametros(leerParametros());
                JOptionPane.showMessageDialog(this, "Parámetros ingresados");
                verificarExistenciaPatametros();
            } else if(btnAccion.getText().equals("Modificar")){
                adminService.editarParametros(leerParametros());
                JOptionPane.showMessageDialog(this, "Parámetros modificados");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Revise los campos, no pueden ser nulos");
        }
    }//GEN-LAST:event_btnAccionActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarTxt();
    }//GEN-LAST:event_btnLimpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccion;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtCantidadPalum;
    private javax.swing.JTextField txtCantidadPprof;
    private javax.swing.JTextField txtDiasPrestamoalum;
    private javax.swing.JTextField txtDiasPrestamoprof;
    private javax.swing.JTextField txtMoraDia;
    // End of variables declaration//GEN-END:variables
}
