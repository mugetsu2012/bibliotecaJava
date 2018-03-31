/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import sv.edu.udb.Data.Conexion;
import sv.edu.udb.Data.modelos.Libro;

/**
 * Servicio que sirve para todo el manejo de libros
 * @author Douglas
 */
public class ItemsService extends ServiceBase {
        
    public ItemsService(){
        super();
    }
    
    /**
     * Inserta un libro en base al objeto libro enviado, regresa el ID del codigo 
     * del libro despues de ser insertado en la base
     * @param libro Objeto libro para ser insertado
     * @return El codigo del libro insertado
     */
    public long insertarLibro(Libro libro){
        
        String querySql  ="insert into item (id_categoria,id_estante,nombre,descripcion,unidades_para_prestar)\n" +
                        "values("+libro.id_categoria+","+libro.id_estante+",'"+libro.nombre+"','"+libro.descripcion+"',"+libro.unidades_para_prestar+")";
        
        long codigoItem = conexion.realizarInsert(querySql);
        
        querySql = "insert into libro(id_item,nota,edicion,fecha_publicacion,lugar_publicacion,isbn)\n" +
                        "values ("+codigoItem+",'"+libro.nota+"', '"+libro.edicion+"','"+libro.fecha_publicacion+"','"+libro.lugar_publicacion+"','"+libro.isbn+"')";
        
        long codigoLibro = conexion.realizarInsert(querySql);
        
        return codigoLibro;
    }    
    
    public void editarLibro(Libro libro){
        
        String querySql = "update item\n" +
            "set id_categoria = "+libro.id_categoria+",id_estante = "+libro.id_estante+", nombre = '"+libro.nombre+"', "
                + "descripcion = '"+libro.descripcion+"', unidades_para_prestar = "+libro.unidades_para_prestar+"\n" +
            "where id_item = "+libro.id_item+"";
        
        conexion.ejecutarQuery(querySql);
        
        querySql = "update libro\n" +
            "set nota = '"+libro.nota+"', edicion = '"+libro.edicion+"',fecha_publicacion = '"+libro.fecha_publicacion+"',"
                + "lugar_publicacion = '"+libro.lugar_publicacion+"', isbn = '"+libro.isbn+"'\n" +
            "where id_libro = "+libro.id_libro+"";
        
        conexion.ejecutarQuery(querySql);    
        
    }
}
