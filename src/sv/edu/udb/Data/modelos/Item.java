/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.Data.modelos;

/**
 *
 * @author Douglas
 */
public class Item {
    public long id_item;    
    public long id_categoria;
    public long id_estante;   
    public String nombre;   
    public String descripcion;
    public int unidades_para_prestar;
    public String nombreCategoria;
    
    
    public long getId_item(){
        return id_item;
    }
    
    public long getId_estante(){
        return id_estante;
    }  
    
    public long getId_categoria(){
        return id_categoria;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getDescripcion(){
        return descripcion;
    }   
    
    public int getUnidades_para_prestar(){
        return unidades_para_prestar;
    }
    
    public String getNombreCategoria(){
        return nombreCategoria;
    }
}
