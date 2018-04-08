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
import sv.edu.udb.Data.modelos.Cd;
import sv.edu.udb.Data.modelos.Estante;
import sv.edu.udb.Data.modelos.Revista;
import sv.edu.udb.Services.CatalogosService;
import sv.edu.udb.Services.ItemsService;
import sv.edu.udb.utiles.ComboItem;
import static sv.udb.edu.modulos.encargados.mantenimientoRevistas.codeRevistaActual;

/**
 *
 * @author DavidMguel
 */
public class mantenimientoCD extends javax.swing.JInternalFrame {

    /**
     * Creates new form mantenimientoCD
     */
    static int bandera = 0;
    static String codeCdActual = null;    
    long idItem = 0;
    long idCd = 0;
    ItemsService itemsService = new ItemsService();
    CatalogosService catalogosService = new CatalogosService();
    public mantenimientoCD() {
        initComponents();
        setOpcionesCategoria();
        setOpcionesEstante();
        buscarDatosTabla(null);
        
        jButton2.setEnabled(false);
        
        //Listener para cuando alguen haga click en un row de la tabla,mandemos a la base a sacar el user
        tablaCds.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent event) {
            if(!event.getValueIsAdjusting() && tablaCds.getSelectedRow() != -1){
                
            //Ir a leer a extraer  el usuario y poblar la tabla
            codeCdActual = tablaCds.getValueAt(tablaCds.getSelectedRow(), 0).toString();             
            renderizarCd();
            
            //Bloquear guardar y habilitar Modificar
            jButton5.setEnabled(false);
            
            jButton2.setEnabled(true);
            }
        }
    });
    }

    private void setOpcionesCategoria(){
        List<Categoria> categorias = catalogosService.getCategorias();
        cmdCategorias.removeAllItems();
        for(Categoria categoria: categorias){
            cmdCategorias.addItem(new ComboItem(categoria.nombre, String.valueOf(categoria.codigo)));
        }
    }
    
    private void setOpcionesEstante(){
        List<Estante> estantes = catalogosService.getEstantes();       
        cmdEstantes.removeAllItems();
        for(Estante estante: estantes){
            cmdEstantes.addItem(new ComboItem(estante.nombre, String.valueOf(estante.codigo)));
        }
    }
    
    private Cd leerCd(){
        ComboItem itemCategoria = (ComboItem)cmdCategorias.getSelectedItem();
        ComboItem itemEstante = (ComboItem)cmdEstantes.getSelectedItem();        
        if(codeCdActual != null){
            Cd cdAnterior = itemsService.getCd(Long.valueOf(codeCdActual));
            idItem = cdAnterior.id_item;
            idCd = cdAnterior.id_cd;
        }       
        
        Cd cd = new Cd(){
            {
                //Parte de ITEM oblitarioa para todos los elementos
                id_item = idItem;
                id_categoria = Long.valueOf(itemCategoria.getValue());
                id_estante = Long.valueOf(itemEstante.getValue());   
                nombre = txtCd.getText();   
                descripcion = txtDescripcion.getText();
                unidades_para_prestar = Integer.valueOf(txtUnidades.getText());
                //Fin parte Item
                id_cd = idCd;
                album = txtAlbum.getText();
                genero = txtGenero.getText();                
                artista = txtArtista.getText();
                fecha_lanzamiento = txtFechaLanzamiento.getText();           
                
                
            }
        };
        
        return cd;
    }    

    private void buscarDatosTabla(String nombre){
        List<Cd> cds = itemsService.getListadoCds();
               
        String[] columnas = {"Cod Cd","Nombre","Descripcion","Genero","Artista", "Categoria"};
        List<String[]> valores = new ArrayList<String[]>();
        
        for(Cd cd : cds){
            valores.add(new String[]{String.valueOf(cd.id_cd),cd.nombre, cd.descripcion,
                cd.genero, cd.artista, cd.nombreCategoria});
        }
        
        DefaultTableModel tableModel = new DefaultTableModel(valores.toArray(new Object[][] {}), columnas);
        tablaCds.setModel(tableModel);        
    }
    
    private void renderizarCd(){
        
        long codeCd = Long.valueOf(codeCdActual);
        Cd cd = itemsService.getCd(codeCd);                
        txtCd.setText(cd.nombre);
        txtDescripcion.setText(cd.descripcion);
        txtUnidades.setText(String.valueOf(cd.unidades_para_prestar));        
        
        
        txtFechaLanzamiento.setText(cd.fecha_lanzamiento);
        
        txtGenero.setText(cd.genero);
        txtAlbum.setText(cd.album);
        txtArtista.setText(cd.artista);
        cmdCategorias.setSelectedIndex(indiceCategoria(cd.id_categoria));
        cmdEstantes.setSelectedIndex(indiceEstante(cd.id_estante));      
        
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCd = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtArtista = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmdCategorias = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtGenero = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        cmdEstantes = new javax.swing.JComboBox();
        txtFechaLanzamiento = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAlbum = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtUnidades = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCds = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Mantenimiento CD");
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("CD"));

        jLabel1.setText("Nombre de CD:");

        jLabel2.setText("Ingrese nombre de artista:");

        jLabel4.setText("Categoría:");

        cmdCategorias.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<sv.edu.udb.utiles.ComboItem>" }));
        cmdCategorias.setSelectedIndex(-1);

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Fecha de lanzamiento:");

        jLabel6.setText("Genero:");

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

        cmdEstantes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<sv.edu.udb.utiles.ComboItem>" }));
        cmdEstantes.setSelectedIndex(-1);

        jLabel3.setText("Albúm: ");

        jLabel7.setText("Unidades prestar");

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
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtCd, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtFechaLanzamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(cmdCategorias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addComponent(jButton5))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(2, 2, 2)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel2)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(txtArtista, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel6)
                                                        .addGap(36, 36, 36)
                                                        .addComponent(txtGenero))))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(17, 17, 17)
                                                .addComponent(jButton2)
                                                .addGap(61, 61, 61)
                                                .addComponent(jButton4)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel3)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(txtAlbum))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel9)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(cmdEstantes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jButton7))))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(txtUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtArtista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmdCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel5)
                    .addComponent(txtFechaLanzamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmdEstantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jButton7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton2)
                    .addComponent(jButton5)))
        );

        tablaCds.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaCds);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (mantenimientoCategoria.bandera == 0) {
                    mantenimientoCategoria categoria = new mantenimientoCategoria();
                    getDesktopPane().add(categoria);
                    categoria.show();
                    mantenimientoCategoria.bandera = 1;
                }
            }
        });
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if(mantenimientoEstantes.bandera==0){
                    mantenimientoEstantes estantes = new mantenimientoEstantes();
                    getDesktopPane().add(estantes);
                    estantes.show();
                    estantes.bandera=1;
                }
            }
        });
    }//GEN-LAST:event_jButton7ActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        bandera = 0;
    }//GEN-LAST:event_formInternalFrameClosing

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        itemsService.insertarCd(leerCd());
        buscarDatosTabla(null);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        itemsService.editarCd(leerCd());
        buscarDatosTabla(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        codeCdActual = null;
        txtCd.setText("");
        txtDescripcion.setText("");
        txtUnidades.setText("0");        
        
        
        txtFechaLanzamiento.setText("");
        
        txtGenero.setText("");
        txtAlbum.setText("");
        txtArtista.setText("");
        cmdCategorias.setSelectedIndex(0);
        cmdEstantes.setSelectedIndex(0);      
        jButton5.setEnabled(true);
        jButton2.setEnabled(false);
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmdCategorias;
    private javax.swing.JComboBox cmdEstantes;
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
    private javax.swing.JTable tablaCds;
    private javax.swing.JTextField txtAlbum;
    private javax.swing.JTextField txtArtista;
    private javax.swing.JTextField txtCd;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtFechaLanzamiento;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextField txtUnidades;
    // End of variables declaration//GEN-END:variables
}
