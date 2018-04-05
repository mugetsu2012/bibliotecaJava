/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
            "values("+codigoItem+",'"+cd.album+"','"+cd.genero+"','"+cd.fecha_lanzamiento+"','"+cd.artista+"')";
        
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
    
    public Item getItem(long codigoItem){
        Item item = new Item();
        
        String query = "select * from item where id_item = "+codigoItem+"";
        ResultSet rs = conexion.RealizarQuery(query);
        
        try{
            while(rs.next()){
                item.id_item = codigoItem;
                item.descripcion = rs.getString("descripcion");
                item.id_categoria = rs.getLong("id_categoria");
                item.id_estante = rs.getLong("id_estante");
                item.nombre = rs.getString("nombre");
                item.unidades_para_prestar = rs.getInt("unidades_para_prestar");
            }
        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }       
        
        return item;
    }
    
    public List<Libro> getListadoLibros(String nombre, Integer idCategoria, String autores){
        
        List<Libro> libros = new ArrayList<Libro>();
        
        String query = "select l.id_libro, i.id_item, l.nota,l.edicion,l.fecha_publicacion, l.lugar_publicacion,l.isbn,l.autores, i.id_categoria, i.id_estante, i.nombre, i.descripcion, i.unidades_para_prestar, c.categoria\n" +
            "from libro as l\n" +
            "inner join item as i on i.id_item = l.id_item\n" +
            "inner join categoria as c on c.id_categoria = i.id_categoria";
        
        ResultSet rs = conexion.RealizarQuery(query);
        
        try {
            while(rs.next()){
                Libro libro = new Libro();
                libro.id_item = rs.getLong("id_item");
                libro.descripcion = rs.getString("descripcion");
                libro.id_categoria = rs.getLong("id_categoria");
                libro.id_estante = rs.getLong("id_estante");
                libro.nombre = rs.getString("nombre");
                libro.unidades_para_prestar = rs.getInt("unidades_para_prestar");
                libro.edicion = rs.getString("edicion");
                libro.lugar_publicacion = rs.getString("lugar_publicacion");
                libro.fecha_publicacion = rs.getString("fecha_publicacion");
                libro.nota = rs.getString("nota");
                libro.id_libro = rs.getLong("id_libro");
                libro.nombreAutor = rs.getString("autores");
                libro.isbn = rs.getString("isbn");
                libro.nombreCategoria = rs.getString("categoria");
                libros.add(libro);                       
            }
        } catch (SQLException e){
            System.out.println("Error: "  + e.getMessage());
        }
        
        //En este punto ya hay libros, toca filtrarlos
        if(!nombre.isEmpty()){
            libros = libros.stream().filter(p -> p.nombre.toLowerCase().contains(nombre.toLowerCase()))
                    .collect(Collectors.toList());
        }
        
        if(!autores.isEmpty()){
            libros = libros.stream().filter(p -> p.nombreAutor.toLowerCase().contains(autores.toLowerCase()))
                    .collect(Collectors.toList());
        }
        
        if(idCategoria != null){          
            libros = libros.stream().filter(p -> p.id_categoria == idCategoria)
                    .collect(Collectors.toList());
        }
        return libros;
        
    }
    
    public Libro getLibro(long codigoLibro){
        Libro libro = new Libro();
        
        String query = "select l.id_libro, i.id_item, l.nota,l.edicion,l.fecha_publicacion, l.lugar_publicacion,l.isbn,l.autores, i.id_categoria, i.id_estante, i.nombre, i.descripcion, i.unidades_para_prestar, c.categoria\n" +
            "from libro as l\n" +
            "inner join item as i on i.id_item = l.id_item\n" +
            "inner join categoria as c on c.id_categoria = i.id_categoria where l.id_libro="+codigoLibro+" LIMIT 1";
        
        ResultSet rs = conexion.RealizarQuery(query);
        
        try {
            while(rs.next()){
                
                libro.id_item = rs.getLong("id_item");
                libro.descripcion = rs.getString("descripcion");
                libro.id_categoria = rs.getLong("id_categoria");
                libro.id_estante = rs.getLong("id_estante");
                libro.nombre = rs.getString("nombre");
                libro.unidades_para_prestar = rs.getInt("unidades_para_prestar");
                libro.edicion = rs.getString("edicion");
                libro.lugar_publicacion = rs.getString("lugar_publicacion");
                libro.fecha_publicacion = rs.getString("fecha_publicacion");
                libro.nota = rs.getString("nota");
                libro.id_libro = rs.getLong("id_libro");
                libro.nombreAutor = rs.getString("autores");
                libro.isbn = rs.getString("isbn");
                libro.nombreCategoria = rs.getString("categoria");                
            }
        } catch (SQLException e){
            System.out.println("Error: "  + e.getMessage());
        }
        
        return libro;
    }
    
    public List<Tesis> getListadoTesis(){
        List<Tesis> tesiss = new ArrayList<Tesis>();
        
        String query = "select t.id_tesis, i.id_item,t.fecha_publicacion,t.lugar_desarrollo, t.autores, i.id_categoria, i.id_estante, i.nombre, i.descripcion, i.unidades_para_prestar, c.categoria\n" +
            "from tesis as t\n" +
            "inner join item as i on i.id_item = t.id_item\n" +
            "inner join categoria as c on c.id_categoria = i.id_categoria";
        
        ResultSet rs = conexion.RealizarQuery(query);
        
        try {
            while(rs.next()){
                Tesis tesis = new Tesis();
                tesis.id_item = rs.getLong("id_item");
                tesis.descripcion = rs.getString("descripcion");
                tesis.id_categoria = rs.getLong("id_categoria");
                tesis.id_estante = rs.getLong("id_estante");
                tesis.nombre = rs.getString("nombre");
                tesis.unidades_para_prestar = rs.getInt("unidades_para_prestar");           
                tesis.fecha_publicacion = rs.getString("fecha_publicacion");
                tesis.id_tesis = rs.getLong("id_tesis");
                tesis.autores = rs.getString("autores");                
                tesis.nombreCategoria = rs.getString("categoria");
                tesis.lugar_desarrollo = rs.getString("lugar_desarrollo");
                tesiss.add(tesis);                       
            }
        } catch (SQLException e){
            System.out.println("Error: "  + e.getMessage());
        }
        
        return tesiss;
        
    }
    
    public Tesis getTesis(long idTesis){
        Tesis tesis = new Tesis();
        
        String query = "select t.id_tesis,t.lugar_desarrollo, i.id_item,t.fecha_publicacion, t.autores, i.id_categoria, i.id_estante, i.nombre, i.descripcion, i.unidades_para_prestar, c.categoria\n" +
            "from tesis as t\n" +
            "inner join item as i on i.id_item = t.id_item\n" +
            "inner join categoria as c on c.id_categoria = i.id_categoria where t.id_tesis = "+idTesis+" LIMIT 1";
        
        ResultSet rs = conexion.RealizarQuery(query);
        
        try {
            while(rs.next()){
                
                tesis.id_item = rs.getLong("id_item");
                tesis.descripcion = rs.getString("descripcion");
                tesis.id_categoria = rs.getLong("id_categoria");
                tesis.id_estante = rs.getLong("id_estante");
                tesis.nombre = rs.getString("nombre");
                tesis.unidades_para_prestar = rs.getInt("unidades_para_prestar");           
                tesis.fecha_publicacion = rs.getString("fecha_publicacion");
                tesis.id_tesis = rs.getLong("id_tesis");
                tesis.autores = rs.getString("autores");                
                tesis.nombreCategoria = rs.getString("categoria");
                tesis.lugar_desarrollo = rs.getString("lugar_desarrollo");
                                     
            }
        } catch (SQLException e){
            System.out.println("Error: "  + e.getMessage());
        }
        
        return tesis;        
    }
    
    public List<Revista> getListadoRevistas(){
        
        List<Revista> revistas = new ArrayList<Revista>();
        
        String query = "select r.id_revista, i.id_item,r.fecha_lanzamiento,r.lugar_lanzamiento, r.editorial,r.edicion, i.id_categoria, i.id_estante, i.nombre, i.descripcion, i.unidades_para_prestar, c.categoria\n" +
            "from revista as r\n" +
            "inner join item as i on i.id_item = r.id_item\n" +
            "inner join categoria as c on c.id_categoria = i.id_categoria";
        
        ResultSet rs = conexion.RealizarQuery(query);
        
        try {
            while(rs.next()){
                Revista revista = new Revista();
                revista.id_item = rs.getLong("id_item");
                revista.descripcion = rs.getString("descripcion");
                revista.id_categoria = rs.getLong("id_categoria");
                revista.id_estante = rs.getLong("id_estante");
                revista.nombre = rs.getString("nombre");
                revista.unidades_para_prestar = rs.getInt("unidades_para_prestar");
                revista.edicion = rs.getString("edicion");
                revista.lugar_publicacion = rs.getString("lugar_lanzamiento");
                revista.fecha_publicacion = rs.getString("fecha_lanzamiento");                
                revista.id_revista = rs.getLong("id_revista");                
                revista.editorial = rs.getString("editorial");
                revista.nombreCategoria = rs.getString("categoria");
                revistas.add(revista);                       
            }
        } catch (SQLException e){
            System.out.println("Error: "  + e.getMessage());
        }
        
        return revistas;
        
    }
    
    public Revista getRevista(long codigoRevista){
        
        Revista revista = new Revista();
        
        String query = "select r.id_revista, i.id_item,r.fecha_lanzamiento,r.lugar_lanzamiento, r.editorial,r.edicion, i.id_categoria, i.id_estante, i.nombre, i.descripcion, i.unidades_para_prestar, c.categoria\n" +
            "from revista as r\n" +
            "inner join item as i on i.id_item = r.id_item\n" +
            "inner join categoria as c on c.id_categoria = i.id_categoria where id_revista="+codigoRevista+" LIMIT 1";
        
        ResultSet rs = conexion.RealizarQuery(query);
        
        try {
            while(rs.next()){
                
                revista.id_item = rs.getLong("id_item");
                revista.descripcion = rs.getString("descripcion");
                revista.id_categoria = rs.getLong("id_categoria");
                revista.id_estante = rs.getLong("id_estante");
                revista.nombre = rs.getString("nombre");
                revista.unidades_para_prestar = rs.getInt("unidades_para_prestar");
                revista.edicion = rs.getString("edicion");
                revista.lugar_publicacion = rs.getString("lugar_lanzamiento");
                revista.fecha_publicacion = rs.getString("fecha_lanzamiento");                
                revista.id_revista = rs.getLong("id_revista");                
                revista.editorial = rs.getString("editorial");
                revista.nombreCategoria = rs.getString("categoria");
                
            }
        } catch (SQLException e){
            System.out.println("Error: "  + e.getMessage());
        }
        
        return revista;
        
    }
    
    public List<Cd> getListadoCds(){
        
        List<Cd> cds = new ArrayList<Cd>();
        
        String query = "select cd.id_cd, i.id_item,cd.fecha_lanzamiento,cd.genero, cd.artista,cd.album, i.id_categoria, i.id_estante, i.nombre, i.descripcion, i.unidades_para_prestar, c.categoria\n" +
            "from cd as cd\n" +
            "inner join item as i on i.id_item = cd.id_item\n" +
            "inner join categoria as c on c.id_categoria = i.id_categoria";
        
        ResultSet rs = conexion.RealizarQuery(query);
        
        try {
            while(rs.next()){
                Cd cd = new Cd();
                cd.id_item = rs.getLong("id_item");
                cd.descripcion = rs.getString("descripcion");
                cd.id_categoria = rs.getLong("id_categoria");
                cd.id_estante = rs.getLong("id_estante");
                cd.nombre = rs.getString("nombre");
                cd.unidades_para_prestar = rs.getInt("unidades_para_prestar");
                cd.album = rs.getString("album");
                cd.genero = rs.getString("genero");
                cd.fecha_lanzamiento = rs.getString("fecha_lanzamiento");                
                cd.id_cd = rs.getLong("id_cd");
                cd.artista = rs.getString("artista");                
                cd.nombreCategoria = rs.getString("categoria");
                cds.add(cd);                       
            }
        } catch (SQLException e){
            System.out.println("Error: "  + e.getMessage());
        }
        
        return cds;
        
    }
    
    public Cd getCd(long codigoCd){
        
        Cd cd = new Cd();
        
        String query = "select cd.id_cd, i.id_item,cd.fecha_lanzamiento,cd.genero, cd.artista,cd.album, i.id_categoria, i.id_estante, i.nombre, i.descripcion, i.unidades_para_prestar, c.categoria\n" +
            "from cd as cd\n" +
            "inner join item as i on i.id_item = cd.id_item\n" +
            "inner join categoria as c on c.id_categoria = i.id_categoria where id_cd="+codigoCd+"";
        
        ResultSet rs = conexion.RealizarQuery(query);
        
        try {
            while(rs.next()){
                
                cd.id_item = rs.getLong("id_item");
                cd.descripcion = rs.getString("descripcion");
                cd.id_categoria = rs.getLong("id_categoria");
                cd.id_estante = rs.getLong("id_estante");
                cd.nombre = rs.getString("nombre");
                cd.unidades_para_prestar = rs.getInt("unidades_para_prestar");
                cd.album = rs.getString("album");
                cd.genero = rs.getString("genero");
                cd.fecha_lanzamiento = rs.getString("fecha_lanzamiento");                
                cd.id_cd = rs.getLong("id_cd");
                cd.artista = rs.getString("artista");                
                cd.nombreCategoria = rs.getString("categoria");
                        
            }
        } catch (SQLException e){
            System.out.println("Error: "  + e.getMessage());
        }
        
        return cd;
        
    }
    
}
