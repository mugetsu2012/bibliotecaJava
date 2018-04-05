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
public class Usuario {
    public String id_carne;
    public long id_catalogo_roles;
    public String passWord;
    public int estado;
    public String nombre;
    public String apellido;
    public String nombreRol;
    
    public String getId_carne(){
        return id_carne;
    }
    
    public long getId_catalogo_roles(){
        return id_catalogo_roles;
    }
    
    public String getPassWord(){
        return passWord;
    }
    
    public int getEstado(){
        return estado;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getApellido(){
        return apellido;
    }
}
