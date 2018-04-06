/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.udb.edu.modulos.encargados;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import sv.edu.udb.Data.modelos.Estante;
import sv.edu.udb.Services.CatalogosService;
import javax.swing.ListSelectionModel;
//
/**
 *
 * @author DavidMguel
 */
public class mantenimientoEstantes extends javax.swing.JInternalFrame {

    /**
     * Creates new form mantenimientoEstantes
     */
    
    static int bandera= 0;
    CatalogosService catalogosService = new CatalogosService();
    
    public mantenimientoEstantes() {
        initComponents();
        tablaEstantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        LlenarTablaEstante();
        
    }

    private void LimpiarTxt(){
        txtNombre.setText("");
        txtSeccion.setText("");
        txtDescripcion.setText("");
    }
    private void LlenarTablaEstante(){
        List<Estante> estantes = catalogosService.getEstantes();
        String[] columnas = {"Estante","Sección", "Descripción"};
        List<String[]> valores = new ArrayList<String[]>();
        for(Estante estante:estantes){
            valores.add(new String[]{String.valueOf(estante.nombre),String.valueOf(estante.seccion),String.valueOf(estante.descripcion)});
        }
        DefaultTableModel tableModel = new DefaultTableModel(valores.toArray(new Object[][]{}),columnas);
        tablaEstantes.setModel(tableModel);
    }
    
    private Estante LeerEstante(){
        Estante estante = new Estante(){
                {
                    nombre=txtNombre.getText();
                    seccion=txtSeccion.getText();
                    descripcion=txtDescripcion.getText();      
                }
        };
        return estante;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEstantes = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtSeccion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Mantenimiento Estantes");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
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

        tablaEstantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaEstantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaEstantesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaEstantes);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Estante"));

        jLabel1.setText("Nombre estante:");

        jLabel2.setText("Sección:");

        jLabel3.setText("Descripción:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre)
                            .addComponent(txtSeccion)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 94, Short.MAX_VALUE)
                        .addComponent(btnGuardar)
                        .addGap(58, 58, 58)
                        .addComponent(btnModificar)
                        .addGap(65, 65, 65)
                        .addComponent(btnLimpiar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnModificar)
                    .addComponent(btnGuardar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        bandera = 0;
    }//GEN-LAST:event_formInternalFrameClosing

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        catalogosService.insertarEstante(LeerEstante());
        LlenarTablaEstante();
        LimpiarTxt();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        catalogosService.editarEstante(LeerEstante());
        LlenarTablaEstante();
        LimpiarTxt();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void tablaEstantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEstantesMouseClicked
        int filaSeleccionada = tablaEstantes.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel)tablaEstantes.getModel();
        txtNombre.setText(modelo.getValueAt(filaSeleccionada,1).toString());
        txtSeccion.setText(modelo.getValueAt(filaSeleccionada, 2).toString());
        txtDescripcion.setText(modelo.getValueAt(filaSeleccionada, 3).toString());
    }//GEN-LAST:event_tablaEstantesMouseClicked

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        LimpiarTxt();
    }//GEN-LAST:event_btnLimpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaEstantes;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSeccion;
    // End of variables declaration//GEN-END:variables
}
