/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.Services;

import java.sql.SQLException;
import sv.edu.udb.Data.Conexion;
import sv.edu.udb.Data.modelos.Libro;

/**
 * Servicio que sirve para todo el manejo de libros
 * @author Douglas
 */
public class LibroService extends ServiceBase {
        
    public LibroService(){
        super();
    }
    
    /**
     * Inserta un libro en base al objeto libro enviado, regresa el ID del codigo 
     * del libro despues de ser insertado en la base
     * @param libro Objeto libro para ser insertado
     * @return El codigo del libro insertado
     */
    public long insertarLibro(Libro libro){
        
        String querySql  ="insert into item (id_categoria,id_estante,nombre,descripcion)\n" +
                        "values("+libro.id_categoria+","+libro.id_estante+",'"+libro.nombre+"','"+libro.descripcion+"')";
        
        long codigoItem = conexion.realizarInsert(querySql);
        
        querySql = "insert into libro(id_item,nota,edicion,fecha_publicacion,lugar_publicacion)\n" +
                        "values ("+codigoItem+",'"+libro.nota+"', '"+libro.edicion+"','"+libro.fecha_publicacion+"','"+libro.lugar_publicacion+"')";
        
        long codigoLibro = conexion.realizarInsert(querySql);
        
        return codigoLibro;
    }
}
