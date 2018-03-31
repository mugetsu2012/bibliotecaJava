/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import sv.edu.udb.Data.Conexion;
import sv.edu.udb.Data.modelos.Autor;
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
    
    public void asignarLibroAutor(long codigoLibro, long codigoAutor){
        
        String query = "select * from libro_autor where id_autor = "+codigoAutor+" and id_libro = "+codigoLibro+"";
        
        ResultSet rs = conexion.RealizarQuery(query);
        
        //Chequear que previamente el libro no tenga a ese autor\
        try{
            if(!rs.isBeforeFirst()){
                //Solo si no hay data hacemos el insert
                query = "insert into libro_autor(id_autor, id_libro) values("+codigoAutor+","+codigoLibro+")";
                conexion.ejecutarQuery(query);                
            }
        }catch(SQLException e){
            
            System.out.println("Error: " + e.getMessage());
        }
        
    }
    
    public void removerAutorLibro(long codigoLibro, long codigoAutor){
        String query = "delete from libro_autor where id_autor = "+codigoAutor+" and id_libro = "+codigoLibro+"";
        conexion.ejecutarQuery(query);
    }
    
    public long insertarAutor(Autor autor){
        
        String query = "INSERT INTO autor(nombre) values('"+autor.nombre+"')";
        
        long code = conexion.realizarInsert(query);
        
        return code;
    }
    
    public void editarAutor(Autor autor){
        
        String query = "UPDATE autor set nombre='"+autor.nombre+"' where id_autor = "+autor.codigo+"";
        
        conexion.ejecutarQuery(query);
        
    }
}
