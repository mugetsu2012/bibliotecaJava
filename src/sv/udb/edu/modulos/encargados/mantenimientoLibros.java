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
import sv.edu.udb.Data.modelos.Estante;
import sv.edu.udb.Data.modelos.Libro;
import sv.edu.udb.Data.modelos.Rol;
import sv.edu.udb.Data.modelos.Usuario;
import sv.edu.udb.Services.CatalogosService;
import sv.edu.udb.Services.ItemsService;
import sv.edu.udb.utiles.ComboItem;

/**
 *
 * @author DavidMguel
 */
public class mantenimientoLibros extends javax.swing.JInternalFrame {

    /**
     * Creates new form mantenimientoLibros
     */
    static int bandera = 0;
    static String codeLibroActual = null;
    static String codeItemActual = null;
    long idItem = 0;
    long idLibro = 0;
    ItemsService itemsService = new ItemsService();
    CatalogosService catalogosService = new CatalogosService();
    
    public mantenimientoLibros() {
        initComponents();
        setOpcionesCategoria();
        setOpcionesEstante();
        buscarDatosTabla(null);
        
        jButton2.setEnabled(false);
        
        //Listener para cuando alguen haga click en un row de la tabla,mandemos a la base a sacar el user
        tablaLibros.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent event) {
            if(!event.getValueIsAdjusting() && tablaLibros.getSelectedRow() != -1){
                
            //Ir a leer a extraer  el usuario y poblar la tabla
            codeLibroActual = tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 0).toString();             
            renderizarLibro();
            
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
        cmbEstante.removeAllItems();
        for(Estante estante: estantes){
            cmbEstante.addItem(new ComboItem(estante.nombre, String.valueOf(estante.codigo)));
        }
    }
    
    private Libro leerLibro(){
        ComboItem itemCategoria = (ComboItem)cmbCategorias.getSelectedItem();
        ComboItem itemEstante = (ComboItem)cmbEstante.getSelectedItem();        
        if(codeLibroActual != null){
            Libro libroAnterior = itemsService.getLibro(Long.valueOf(codeLibroActual));
            idItem = libroAnterior.id_item;
            idLibro = libroAnterior.id_libro;
        }       
        
        Libro libro = new Libro(){
            {
                //Parte de ITEM oblitarioa para todos los elementos
                id_item = idItem;
                id_categoria = Long.valueOf(itemCategoria.getValue());
                id_estante = Long.valueOf(itemEstante.getValue());   
                nombre = txtNombreLibro.getText();   
                descripcion = txtDescripcion.getText();
                unidades_para_prestar = Integer.valueOf(txtUnidades.getText());
                //Fin parte Item
                id_libro = idLibro;
                nota = txtNota.getText();
                edicion = txtEdicion.getText();
                fecha_publicacion = txtFechaPublicacion.getText();
                lugar_publicacion = txtLugarPublicacion.getText();   
                nombreAutor = txtNombreAutor.getText();
                isbn = txtIsbn.getText();
                
                
            }
        };
        
        return libro;
    }    

    private void buscarDatosTabla(String nombre){
        List<Libro> libros = itemsService.getListadoLibros("", null,"");
               
        String[] columnas = {"Cod Libr","Nombre","Descripcion","Autores","Edicion", "Categoria"};
        List<String[]> valores = new ArrayList<String[]>();
        
        for(Libro libro : libros){
            valores.add(new String[]{String.valueOf(libro.id_libro),libro.nombre, libro.descripcion,
                libro.nombreAutor, libro.edicion, libro.nombreCategoria});
        }
        
        DefaultTableModel tableModel = new DefaultTableModel(valores.toArray(new Object[][] {}), columnas);
        tablaLibros.setModel(tableModel);        
    }
    
    private void renderizarLibro(){
        
        long codeLibro = Long.valueOf(codeLibroActual);
        Libro libro = itemsService.getLibro(codeLibro);                
        txtNombreLibro.setText(libro.nombre);
        txtDescripcion.setText(libro.descripcion);
        txtUnidades.setText(String.valueOf(libro.unidades_para_prestar));        
        txtNota.setText(libro.nota);
        txtEdicion.setText(libro.edicion);
        txtFechaPublicacion.setText(libro.fecha_publicacion);
        txtLugarPublicacion.setText(libro.lugar_publicacion);   
        txtNombreAutor.setText(libro.nombreAutor);
        txtIsbn.setText(libro.isbn);       
        cmbCategorias.setSelectedIndex(indiceCategoria(libro.id_categoria));
        cmbEstante.setSelectedIndex(indiceEstante(libro.id_estante));      
        
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

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombreLibro = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombreAutor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEdicion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbCategorias = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtLugarPublicacion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNota = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        cmbEstante = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        txtIsbn = new javax.swing.JTextField();
        txtFechaPublicacion = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtUnidades = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaLibros = new javax.swing.JTable();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Mantenimiento Libros");
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Libros"));

        jLabel1.setText("Nombre del libro:");

        jLabel2.setText("Ingrese nombre del autor:");

        jLabel3.setText("Edicion:");

        jLabel4.setText("Categoría:");

        cmbCategorias.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<sv.edu.udb.utiles.ComboItem>" }));
        cmbCategorias.setToolTipText("");

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Fecha de publicación:");

        jLabel6.setText("Lugar de publicación:");

        jLabel7.setText("Notas:");

        jLabel8.setText("Descripción:");

        txtNota.setColumns(20);
        txtNota.setRows(5);
        jScrollPane2.setViewportView(txtNota);

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

        cmbEstante.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<sv.edu.udb.utiles.ComboItem>" }));
        cmbEstante.setSelectedIndex(-1);

        jLabel10.setText("ISBN:");

        jLabel11.setText("Unidades prestar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton5))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(10, 10, 10)
                                        .addComponent(txtFechaPublicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel3))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(36, 36, 36)
                                                .addComponent(txtEdicion, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(txtNombreLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(61, 61, 61)
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addComponent(cmbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtIsbn))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtNombreAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(36, 36, 36)
                                    .addComponent(txtLugarPublicacion))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel9))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cmbEstante, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane3))))
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addGap(9, 9, 9)))))
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(txtLugarPublicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaPublicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(cmbEstante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton2)
                    .addComponent(jButton4)))
        );

        tablaLibros.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaLibros);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
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
        itemsService.insertarLibro(leerLibro());
        buscarDatosTabla(null);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        itemsService.editarLibro(leerLibro());
        buscarDatosTabla(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        txtNombreLibro.setText("");
        txtDescripcion.setText("");
        txtUnidades.setText(String.valueOf(0));        
        txtNota.setText("");
        txtEdicion.setText("");
        txtLugarPublicacion.setText("");   
        txtNombreAutor.setText("");
        txtIsbn.setText("");       
        cmbCategorias.setSelectedIndex(0);
        cmbEstante.setSelectedIndex(0);   
        jButton5.setEnabled(true);
        jButton2.setEnabled(false);
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbCategorias;
    private javax.swing.JComboBox cmbEstante;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaLibros;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtEdicion;
    private javax.swing.JTextField txtFechaPublicacion;
    private javax.swing.JTextField txtIsbn;
    private javax.swing.JTextField txtLugarPublicacion;
    private javax.swing.JTextField txtNombreAutor;
    private javax.swing.JTextField txtNombreLibro;
    private javax.swing.JTextArea txtNota;
    private javax.swing.JTextField txtUnidades;
    // End of variables declaration//GEN-END:variables
}
