/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.udb.edu.modulos.encargados;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import sv.edu.udb.Data.modelos.Categoria;
import sv.edu.udb.Data.modelos.DatosPersonales;
import sv.edu.udb.Data.modelos.Libro;
import sv.edu.udb.Data.modelos.Rol;
import sv.edu.udb.Data.modelos.Usuario;
import sv.edu.udb.Services.AdminService;
import sv.edu.udb.utiles.ComboItem;

/**
 *
 * @author DavidMguel
 */
public class mantenimientoUsuario extends javax.swing.JInternalFrame {

    AdminService adminService = new AdminService(); 
    /**
     * Creates new form mantenimientoUsuario
     */
    static int bandera = 0;
    public mantenimientoUsuario() {
        initComponents();
        setOpcionesRol();
        buscarDatosTabla(null);
        rdoMas.setSelected(true);
        jButton3.setEnabled(false);
        
        //Listener para cuando alguen haga click en un row de la tabla,mandemos a la base a sacar el user
        tablaUsuarios.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent event) {
            if(!event.getValueIsAdjusting() && tablaUsuarios.getSelectedRow() != -1){
                
            //Ir a leer a extraer  el usuario y poblar la tabla
            renderizarUsuario(tablaUsuarios.getValueAt(tablaUsuarios.getSelectedRow(), 0).toString());
            
            //Bloquear el boton guardar y el modificador de carne
            jButton1.setEnabled(false);
            txtCarne.setEnabled(false);
            jButton3.setEnabled(true);
            }
        }
    });

    }
    
    private void setOpcionesRol(){
        List<Rol> roles = adminService.getListaRoles();       
        cmbRoles.removeAllItems();
        for(Rol rol: roles){
            cmbRoles.addItem(new ComboItem(rol.rol, String.valueOf(rol.id_catalogo_roles)));
        }
    }
    
    private Usuario leerUsuario(){
        ComboItem item = (ComboItem)cmbRoles.getSelectedItem();
        Usuario usuario = new Usuario()
        {
            {
                id_carne = txtCarne.getText();
                id_catalogo_roles = Long.valueOf(item.getValue());
                passWord = txtPassword.getText();
                estado = 1;
            }
        };
        
        return usuario;
    }
    
    private DatosPersonales leerDatosPersonales(){
        DatosPersonales dp = new DatosPersonales(){
            {
                nombre = txtNombres.getText();
                apellido = txtApellidos.getText();
                genero = rdoMas.isSelected() ? 1 : 0;
                email = txtEmail.getText();
                telefono = txtTelefono.getText();
                direccion = txtDireccion.getText();
            }
        };
        
        return dp;
    }

    private void buscarDatosTabla(String nombre){
        List<Usuario> usuarios = adminService.getListaUsuarios(null, 0, null);
        
        if(nombre != null && !nombre.isEmpty()){
            usuarios = usuarios.stream().filter(p -> p.nombre.toLowerCase().contains(nombre.toLowerCase()) || p.apellido.toLowerCase().contains(nombre.toLowerCase()))
                    .collect(Collectors.toList());
        }
        
        
        String[] columnas = {"Carne","Rol","Nombre","Apellido"};
        List<String[]> valores = new ArrayList<String[]>();
        
        for(Usuario usuario : usuarios){
            valores.add(new String[]{usuario.id_carne, usuario.nombreRol, usuario.nombre, usuario.apellido});
        }
        
        DefaultTableModel tableModel = new DefaultTableModel(valores.toArray(new Object[][] {}), columnas);
        tablaUsuarios.setModel(tableModel);        
    }
    
    private void renderizarUsuario(String carne){
        Usuario user = adminService.getUsuario(carne);
        DatosPersonales dp = adminService.getDatosPersonales(carne);
        txtCarne.setText(user.id_carne);
        cmbRoles.setSelectedIndex(indiceRol(user.id_catalogo_roles));
        txtPassword.setText(user.passWord);
        txtNombres.setText(dp.nombre);
        txtApellidos.setText(dp.apellido);
        if(dp.genero == 1){
            rdoMas.setSelected(true);
            rdoFem.setSelected(false);
        }
        else {
            rdoMas.setSelected(false);
            rdoFem.setSelected(true);        
        }
        txtEmail.setText(dp.email);
        txtTelefono.setText(dp.telefono);
        txtDireccion.setText(dp.direccion);
    }
    
    private int indiceRol(long idRol){
        List<Rol> roles = adminService.getListaRoles();  
        Rol rol = roles.stream().filter(p -> p.id_catalogo_roles == idRol).collect(Collectors.toList()).get(0);
        return roles.indexOf(rol);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtApellidos = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        rdoMas = new javax.swing.JRadioButton();
        rdoFem = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        txtCarne = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cmbRoles = new javax.swing.JComboBox();
        txtEmail = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        txtBuscador = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Manteminiento Usuario");
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

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaUsuarios);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuario"));

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Ingresar nombres: ");

        jButton2.setText("Limpiar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Ingresar apellidos:");

        rdoMas.setText("Masculino");
        rdoMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMasActionPerformed(evt);
            }
        });

        rdoFem.setText("Femenino");
        rdoFem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoFemActionPerformed(evt);
            }
        });

        jLabel3.setText("Ingrese email");

        jLabel4.setText("Ingrese password");

        jLabel8.setText("Genero");

        jLabel5.setText("Ingrese usuario");

        jLabel6.setText("Telefono:");

        jLabel7.setText("Direccion");

        jButton3.setText("Modificar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel9.setText("Tipo usuario:");

        cmbRoles.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<sv.edu.udb.utiles.ComboItem>" }));
        cmbRoles.setSelectedIndex(-1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(jLabel9)
                .addGap(26, 26, 26)
                .addComponent(cmbRoles, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(291, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)
                            .addComponent(txtCarne, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18)
                            .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel3))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addGap(4, 4, 4)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jButton1)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jButton2))))))
                                .addComponent(jLabel7))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel8))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(20, 20, 20)
                                            .addComponent(rdoMas)
                                            .addGap(26, 26, 26)
                                            .addComponent(rdoFem)
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                        .addComponent(txtApellidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(43, 43, 43)
                                    .addComponent(jButton3)
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(183, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbRoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(95, 95, 95))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtCarne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdoMas)
                        .addComponent(rdoFem)
                        .addComponent(jLabel8))
                    .addGap(89, 89, 89)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(jButton3))
                    .addContainerGap(38, Short.MAX_VALUE)))
        );

        jButton5.setText("Buscar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        bandera = 0;
    }//GEN-LAST:event_formInternalFrameClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:       
        adminService.crearUsuario(leerUsuario(), leerDatosPersonales());
        buscarDatosTabla(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void rdoMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMasActionPerformed
        // TODO add your handling code here:
        rdoFem.setSelected(false);
        rdoMas.setSelected(true);
    }//GEN-LAST:event_rdoMasActionPerformed

    private void rdoFemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoFemActionPerformed
        // TODO add your handling code here:
        rdoFem.setSelected(true);
        rdoMas.setSelected(false);
    }//GEN-LAST:event_rdoFemActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jButton1.setEnabled(true);
        txtCarne.setEnabled(true);
        jButton3.setEnabled(false);
        txtCarne.setText("");
        cmbRoles.setSelectedIndex(0);
        txtPassword.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        rdoMas.setSelected(true);
        rdoFem.setSelected(false);
        txtEmail.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        tablaUsuarios.clearSelection();
        tablaUsuarios.getSelectionModel().clearSelection();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Usuario user = leerUsuario();
        DatosPersonales dp = leerDatosPersonales();
        dp.id_carne = user.id_carne;
        adminService.editarDatosPersonales(dp);
        adminService.editarUsuario(user);
        buscarDatosTabla(null);
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox cmbRoles;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoFem;
    private javax.swing.JRadioButton rdoMas;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtCarne;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
