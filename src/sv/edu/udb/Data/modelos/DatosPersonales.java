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
public class DatosPersonales {
    public long id_datos_personales;
    public String id_carne;
    public String nombre;
    public String apellido;
    public int genero;
    public String email;
    public String telefono;
    public String direccion;
    
    
    public long getId_datos_personales(){
        return id_datos_personales;
    }
    
    public String getId_carne(){
        return id_carne;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getApellido(){
        return apellido;
    }
    
    public int getGenero(){
        return genero;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getTelefono(){
        return telefono;
    }
    
    public String getDireccion(){
        return direccion;
    }
}
