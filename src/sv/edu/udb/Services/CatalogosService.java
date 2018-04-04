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
import sv.edu.udb.Data.Conexion;
import sv.edu.udb.Data.modelos.Categoria;
import sv.edu.udb.Data.modelos.Estante;
import sv.edu.udb.Data.modelos.Libro;

/**
 * Servicio que va a servir para manejar los catalogos sin importancia
 * como lo son las categorias, autores, estantes,etc
 * @author Douglas
 */
public class CatalogosService extends ServiceBase {
    
    
    public CatalogosService(){
        super();
    }
    
    /**
     * Inserta un libro en base al objeto libro enviado, regresa el ID del codigo 
     * del libro despues de ser insertado en la base
     */
    public long insertarCategoria(Categoria categoria){
        
        String querySql ="INSERT INTO categoria(categoria, descripcion) values ('"+categoria.nombre+"','"+categoria.descripcion+"')";
        long codigoCategoria = conexion.realizarInsert(querySql);
        return codigoCategoria;
    }
    
    public void editarCategoria(Categoria categoria){
        
        String querySql = "update categoria\n" +
            "set categoria = '"+categoria.nombre+"', descripcion = '"+categoria.descripcion+"'\n" +
            "where id_categoria = "+categoria.codigo+"";
        
        conexion.ejecutarQuery(querySql);
    }
    
    public long insertarEstante(Estante estante){
        String querySql ="INSERT INTO estante(estante,seccion, descripcion) values ('"+estante.nombre+"','"+estante.seccion+"','"+estante.descripcion+"')";
        long codigoEstante = conexion.realizarInsert(querySql);
        return codigoEstante;
    }
    
    public void editarEstante(Estante estante){
        
        String query = "update estante set estante = '"+estante.nombre+"', seccion = '"+estante.seccion+"',"
                + " descripcion = '"+estante.descripcion+"' where id_estante = "+estante.codigo+"";
        conexion.ejecutarQuery(query);
    }
    
    public List<Estante> getEstantes(){
        List<Estante> estantes = new ArrayList<Estante>();
        
        String query = "select * from estante";
        ResultSet rs = conexion.RealizarQuery(query);
        
        try{
            while(rs.next()){
                Estante estante = new Estante();
                estante.codigo = rs.getLong("id_estante");
                estante.nombre = rs.getString("estante");
                estante.seccion = rs.getString("seccion");
                estante.descripcion = rs.getString("descripcion");
                estantes.add(estante);
            }
        } catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return estantes;
    }
    
    public List<Categoria> getCategorias(){
        List<Categoria> categorias = new ArrayList<Categoria>();
        
        String query = "select * from categoria";
        ResultSet rs = conexion.RealizarQuery(query);
        
        try{
            while(rs.next()){
                Categoria categoria = new Categoria();
                categoria.codigo = rs.getLong("id_categoria");
                categoria.nombre = rs.getString("categoria");
                categoria.descripcion = rs.getString("descripcion");
                categorias.add(categoria);
            }
        } catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return categorias;
    }
    
    
    
    /**
     * Metodo para eliminar todas las categorias.
     * <b>Este metodo es solo de prueba, sera removido mas adelante</b>
     */
    public void eliminarTodasCategorias(){
        String querySql = "DELETE FROM categoria";
        conexion.ejecutarQuery(querySql);
    }
}
