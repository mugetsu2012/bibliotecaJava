/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.Data.modelos;

/**
 * Clase que modelo al objeto estante de la base de datos
 * @author Douglas
 */
public class Estante {
    public long codigo;
    public String nombre;
    public String descripcion;
    
    public long getCodigo(){
        return codigo;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
}
