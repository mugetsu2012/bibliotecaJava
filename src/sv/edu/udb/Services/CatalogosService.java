/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.Services;

import java.sql.SQLException;
import sv.edu.udb.Data.Conexion;
import sv.edu.udb.Data.modelos.Categoria;
import sv.edu.udb.Data.modelos.Libro;

/**
 * Servicio que va a servir para manejar los catalogos sin importancia
 * como lo son las categorias, autores, estantes,etc
 * @author Douglas
 */
public class CatalogosService {
    Conexion conexion = null;
    
    public CatalogosService(){
        try
        {
            conexion = new Conexion();
        }
        catch(SQLException e){
            System.out.println("ERROR:No se pudo construir el objeto de conexion:" + e.getMessage());
        }
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
    
    /**
     * Metodo para eliminar todas las categorias.
     * <b>Este metodo es solo de prueba, sera removido mas adelante</b>
     */
    public void eliminarTodasCategorias(){
        String querySql = "DELETE FROM categoria";
        conexion.ejecutarQuery(querySql);
    }
}
