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
    public long id_usuario;
    public String usuario;
    public String passWord;
    public int estado;
    
    public long getId_usuario(){
        return id_usuario;
    }
    
    public String getUsuario(){
        return usuario;
    }
    
    public String getPassWord(){
        return passWord;
    }
    
    public int getEstado(){
        return estado;
    }
}
