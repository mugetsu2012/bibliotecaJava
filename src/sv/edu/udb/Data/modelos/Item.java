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
    public long id_autor;
    public String nombre;   
    public String descripcion;
    public String nombreAutor;
    
    public long getId_item(){
        return id_item;
    }
    
    public long getId_estante(){
        return id_estante;
    }
    
    public long getId_autor(){
        return id_autor;
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
    
    public String getNombreAutor(){
        return nombreAutor;
    }
}
