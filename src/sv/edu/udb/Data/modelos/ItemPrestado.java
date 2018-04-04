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
public class ItemPrestado {
    public String nombre;
    public String descripcion;
    public int cantidadPrestamos;
    
    public String getNombre(){
        return nombre;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public int getCantidadPrestamos(){
        return cantidadPrestamos;
    }  
    
}
