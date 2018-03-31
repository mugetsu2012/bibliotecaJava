/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.Data.modelos;

/**
 * Cambio prueba
 * @author Douglas
 */
public class Libro extends Item {
    public long id_libro;    
    public String nota;
    public String edicion;
    public String fecha_publicacion;
    public String lugar_publicacion;   
    public String nombreAutor;
    public String isbn;
    
    public String getEdicion(){
        return edicion;
    }
    
    public String getLugar_publicacion(){
        return lugar_publicacion;
    }
    
    public String getFecha_publicacion(){
        return fecha_publicacion;
    }
    
    public String getNota(){
        return nota;
    }
    
    public long getId_libro(){
        return id_libro;
    }
         
    public String getNombreAutor(){
        return nombreAutor;
    }
    
    public String getIsbn(){
        return isbn;
    }
}
