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
import sv.edu.udb.Data.modelos.Estante;
import sv.edu.udb.Data.modelos.Revista;
import sv.edu.udb.Data.modelos.Tesis;
import sv.edu.udb.Services.CatalogosService;
import sv.edu.udb.Services.ItemsService;
import sv.edu.udb.utiles.ComboItem;
import static sv.udb.edu.modulos.encargados.mantenimientoTesis.codeTesisActual;

/**
 *
 * @author DavidMguel
 */
public class mantenimientoRevistas extends javax.swing.JInternalFrame {

    /**
     * Creates new form mantenimientoRevistas
     */
    
    static int bandera = 0;
    static String codeRevistaActual = null;    
    long idItem = 0;
    long idRevista = 0;
    ItemsService itemsService = new ItemsService();
    CatalogosService catalogosService = new CatalogosService();
    
    
    public mantenimientoRevistas() {
        initComponents();
        setOpcionesCategoria();
        setOpcionesEstante();
        buscarDatosTabla(null);
        
        jButton2.setEnabled(false);
        
        //Listener para cuando alguen haga click en un row de la tabla,mandemos a la base a sacar el user
        tablaRevistas.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent event) {
            if(!event.getValueIsAdjusting() && tablaRevistas.getSelectedRow() != -1){
                
            //Ir a leer a extraer  el usuario y poblar la tabla
            codeRevistaActual = tablaRevistas.getValueAt(tablaRevistas.getSelectedRow(), 0).toString();             
            renderizarRevista();
            
            //Bloquear guardar y habilitar Modificar
            jButton5.setEnabled(false);
            
            jButton2.setEnabled(true);
            }
        }
    });
    
        
    }
    
    private void setOpcionesCategoria(){
        List<Categoria> categorias = catalogosService.getCategorias();
        cmbCategorias.removeAllItems();
        for(Categoria categoria: categorias){
            cmbCategorias.addItem(new ComboItem(categoria.nombre, String.valueOf(categoria.codigo)));
        }
    }
    
    private void setOpcionesEstante(){
        List<Estante> estantes = catalogosService.getEstantes();       
        cmbEstantes.removeAllItems();
        for(Estante estante: estantes){
            cmbEstantes.addItem(new ComboItem(estante.nombre, String.valueOf(estante.codigo)));
        }
    }
    
    private Revista leerRevista(){
        ComboItem itemCategoria = (ComboItem)cmbCategorias.getSelectedItem();
        ComboItem itemEstante = (ComboItem)cmbEstantes.getSelectedItem();        
        if(codeRevistaActual != null){
            Revista revistaAnterior = itemsService.getRevista(Long.valueOf(codeRevistaActual));
            idItem = revistaAnterior.id_item;
            idRevista = revistaAnterior.id_revista;
        }       
        
        Revista revista = new Revista(){
            {
                //Parte de ITEM oblitarioa para todos los elementos
                id_item = idItem;
                id_categoria = Long.valueOf(itemCategoria.getValue());
                id_estante = Long.valueOf(itemEstante.getValue());   
                nombre = txtNombreRevista.getText();   
                descripcion = txtDescripcion.getText();
                unidades_para_prestar = Integer.valueOf(txtUnidades.getText());
                //Fin parte Item
                id_revista = idRevista;
                edicion = txtEdicion.getText();
                editorial = txtEditorial.getText();                
                lugar_publicacion = txtLugarLanzamiento.getText();
                fecha_publicacion = txtFechaLanzamiento.getText();           
                
                
            }
        };
        
        return revista;
    }    

    private void buscarDatosTabla(String nombre){
        List<Revista> revistas = itemsService.getListadoRevistas();
               
        String[] columnas = {"Cod Revista","Nombre","Descripcion","Editorial","Fecha Lanzamiento", "Categoria"};
        List<String[]> valores = new ArrayList<String[]>();
        
        for(Revista revista : revistas){
            valores.add(new String[]{String.valueOf(revista.id_revista),revista.nombre, revista.descripcion,
                revista.editorial, revista.fecha_publicacion, revista.nombreCategoria});
        }
        
        DefaultTableModel tableModel = new DefaultTableModel(valores.toArray(new Object[][] {}), columnas);
        tablaRevistas.setModel(tableModel);        
    }
    
    private void renderizarRevista(){
        
        long codeRevista = Long.valueOf(codeRevistaActual);
        Revista revista = itemsService.getRevista(codeRevista);                
        txtNombreRevista.setText(revista.nombre);
        txtDescripcion.setText(revista.descripcion);
        txtUnidades.setText(String.valueOf(revista.unidades_para_prestar));        
        
        
        txtFechaLanzamiento.setText(revista.fecha_publicacion);
        txtLugarLanzamiento.setText(revista.lugar_publicacion);   
        txtEditorial.setText(revista.editorial);
        txtEdicion.setText(revista.edicion);
        
        cmbCategorias.setSelectedIndex(indiceCategoria(revista.id_categoria));
        cmbEstantes.setSelectedIndex(indiceEstante(revista.id_estante));      
        
    }
    
    private int indiceCategoria(long idCategoria){
        List<Categoria> categorias = catalogosService.getCategorias();  
        Categoria categoria = categorias.stream().filter(p -> p.codigo == idCategoria).collect(Collectors.toList()).get(0);
        return categorias.indexOf(categoria);
    }
    
    private int indiceEstante(long idEstante){
        List<Estante> estantes = catalogosService.getEstantes();  
        Estante estante = estantes.stream().filter(p -> p.codigo == idEstante).collect(Collectors.toList()).get(0);
        return estantes.indexOf(estante);
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
        tablaRevistas = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombreRevista = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtEditorial = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEdicion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbCategorias = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtLugarLanzamiento = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        cmbEstantes = new javax.swing.JComboBox();
        txtFechaLanzamiento = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtUnidades = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Mantenimiento Revistas");
        setToolTipText("");
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

        tablaRevistas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaRevistas);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Revista"));

        jLabel1.setText("Nombre de revista:");

        jLabel2.setText("Ingrese nombre de editorial:");

        jLabel3.setText("Edicion:");

        jLabel4.setText("Categoría:");

        cmbCategorias.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<sv.edu.udb.utiles.ComboItem>" }));
        cmbCategorias.setSelectedIndex(-1);

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Fecha de lanzamiento:");

        jLabel6.setText("Lugar de lanzamiento:");

        jLabel8.setText("Descripción:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane3.setViewportView(txtDescripcion);

        jButton2.setText("Modificar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Limpiar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Guardar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel9.setText("Estante:");

        jButton7.setText("+");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        cmbEstantes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<sv.edu.udb.utiles.ComboItem>" }));
        cmbEstantes.setSelectedIndex(-1);
        cmbEstantes.setToolTipText("");

        jLabel7.setText("Cantidad Prestar:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtFechaLanzamiento, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel3))
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtNombreRevista, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                            .addComponent(txtEdicion))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addComponent(jButton5)
                                                .addGap(193, 193, 193)
                                                .addComponent(jButton2)
                                                .addGap(61, 61, 61)
                                                .addComponent(jButton4))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                                                .addComponent(cmbEstantes, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton7))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel2)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(txtEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel6)
                                                    .addGap(36, 36, 36)
                                                    .addComponent(txtLugarLanzamiento)))
                                            .addComponent(jLabel4))))))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreRevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cmbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(txtLugarLanzamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaLanzamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbEstantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jButton7))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton2)
                    .addComponent(jButton5)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mantenimientoJCategoria().setVisible(true);
            }
        });

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mantenimientoJEstante().setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton7ActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        bandera = 0;
    }//GEN-LAST:event_formInternalFrameClosing

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        itemsService.insertarRevista(leerRevista());
        buscarDatosTabla(null);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        itemsService.editarRevista(leerRevista());
        buscarDatosTabla(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        txtNombreRevista.setText("");
        txtDescripcion.setText("");
        txtUnidades.setText(String.valueOf(""));        
        
        
        txtFechaLanzamiento.setText("");
        txtLugarLanzamiento.setText("");   
        txtEditorial.setText("");
        txtEdicion.setText("");
        
        cmbCategorias.setSelectedIndex(0);
        cmbEstantes.setSelectedIndex(0);      
        jButton5.setEnabled(true);
        jButton2.setEnabled(false);
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbCategorias;
    private javax.swing.JComboBox cmbEstantes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaRevistas;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtEdicion;
    private javax.swing.JTextField txtEditorial;
    private javax.swing.JTextField txtFechaLanzamiento;
    private javax.swing.JTextField txtLugarLanzamiento;
    private javax.swing.JTextField txtNombreRevista;
    private javax.swing.JTextField txtUnidades;
    // End of variables declaration//GEN-END:variables
}
