/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import sv.edu.udb.Data.Conexion;
import sv.edu.udb.Data.modelos.Cd;
import sv.edu.udb.Data.modelos.Item;
import sv.edu.udb.Data.modelos.Libro;
import sv.edu.udb.Data.modelos.Revista;
import sv.edu.udb.Data.modelos.Tesis;

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
        
        
        long codigoItem = insertarItem(libro);
        
        String querySql = "insert into libro(id_item,nota,edicion,fecha_publicacion,lugar_publicacion,isbn,autores)\n" +
                        "values ("+codigoItem+",'"+libro.nota+"', '"+libro.edicion+"','"+libro.fecha_publicacion+"','"+libro.lugar_publicacion+"','"+libro.isbn+"', '"+libro.nombreAutor+"')";
        
        long codigoLibro = conexion.realizarInsert(querySql);
        
        return codigoLibro;
    }    
    
    public void editarLibro(Libro libro){
        
        editarItem(libro);
        String querySql = "update libro\n" +
            "set nota = '"+libro.nota+"', edicion = '"+libro.edicion+"',fecha_publicacion = '"+libro.fecha_publicacion+"',"
                + "lugar_publicacion = '"+libro.lugar_publicacion+"', isbn = '"+libro.isbn+"', autores='"+libro.nombreAutor+"'\n" +
            "where id_libro = "+libro.id_libro+"";
        
        conexion.ejecutarQuery(querySql);        
    }
    
    public long insertarCd(Cd cd){
        
        long codigoItem = insertarItem(cd);
        
        String query = "insert into cd(id_item,album,genero,fecha_lanzamiento,artista)\n" +
            "values("+cd.id_item+",'"+cd.album+"','"+cd.genero+"','"+cd.fecha_lanzamiento+"','"+cd.artista+"')";
        
        long codigoCd = conexion.realizarInsert(query);
        
        return codigoCd;
    }
    
    public void editarCd(Cd cd){
        editarItem(cd);
        
        String querySql = "update cd\n" +
            "set album = '"+cd.album+"', genero='"+cd.genero+"',fecha_lanzamiento = '"+cd.fecha_lanzamiento+"',artista='"+cd.artista+"'\n" +
            "where id_cd = "+cd.id_cd+"";
        
        conexion.ejecutarQuery(querySql);
    }
    
    public long insertarRevista(Revista revista){
          
        long codigoItem = insertarItem(revista);
        
        String query = "insert into revista(id_item, edicion, editorial, lugar_lanzamiento,fecha_lanzamiento)\n" +
            "values("+codigoItem+",'"+revista.edicion+"','"+revista.editorial+"','"+revista.editorial+"','"+revista.fecha_publicacion+"')";
        
        long codigoCd = conexion.realizarInsert(query);
        
        return codigoCd;
    }
    
    public void editarRevista(Revista revista){
        editarItem(revista);
        
        String querySql = "update revista\n" +
            "set edicion = '"+revista.edicion+"',editorial='"+revista.editorial+"',"
                + "lugar_lanzamiento='"+revista.lugar_publicacion+"',fecha_lanzamiento='"+revista.fecha_publicacion+"'\n" +
            "where id_revista = "+revista.id_revista+"";
        
        conexion.ejecutarQuery(querySql);
    }
    
    public long insertarTesis(Tesis tesis){
        
        long codigoItem = insertarItem(tesis);
        
        String query = "insert into tesis(id_item, fecha_publicacion,lugar_desarrollo, autores)\n" +
            "values("+codigoItem+",'"+tesis.fecha_publicacion+"','"+tesis.lugar_desarrollo+"','"+tesis.autores+"')";
        
        long codigoCd = conexion.realizarInsert(query);
        
        return codigoCd;
    }
    
    public void editarTesis(Tesis tesis){
        editarItem(tesis);
        
        String querySql = "update tesis\n" +
            "set fecha_publicacion = '"+tesis.fecha_publicacion+"', lugar_desarrollo='"+tesis.lugar_desarrollo+"',autores = '"+tesis.autores+"'\n" +
            "where id_tesis = "+tesis.id_tesis+"";
        
        conexion.ejecutarQuery(querySql);
    }
    
    private long insertarItem(Item item){
        String querySql  ="insert into item (id_categoria,id_estante,nombre,descripcion,unidades_para_prestar)\n" +
                        "values("+item.id_categoria+","+item.id_estante+",'"+item.nombre+"','"+item.descripcion+"',"+item.unidades_para_prestar+")";
        
        long codigoItem = conexion.realizarInsert(querySql);
        return codigoItem;
    }
    
    private void editarItem(Item item){
        String querySql = "update item\n" +
            "set id_categoria = "+item.id_categoria+",id_estante = "+item.id_estante+", nombre = '"+item.nombre+"', "
                + "descripcion = '"+item.descripcion+"', unidades_para_prestar = "+item.unidades_para_prestar+"\n" +
            "where id_item = "+item.id_item+"";
        
        conexion.ejecutarQuery(querySql);
    }
    
    
}
