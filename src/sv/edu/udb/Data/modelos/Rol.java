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
public class Rol {
    public long id_catalogo_roles;
    public String rol;
    public String descripcion;
    
    public long getId_catalogo_roles(){
        return id_catalogo_roles;
    }
    
    public String getRol(){
        return rol;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
}
