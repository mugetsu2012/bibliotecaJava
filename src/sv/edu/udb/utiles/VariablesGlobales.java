/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.utiles;

/**
 *
 * @author hilde
 */
public class VariablesGlobales {
    static String codigoEncargado="";
    static int tipoUsuario=-1;
    
    public static void setCodigoEncargado(String codigo){
        codigoEncargado=codigo;
    }
    
    public static String getCodigoEncargado(){
        return codigoEncargado;
    }    
    
    public static void setTipoUsuario(int tipo){
        tipoUsuario=tipo;
    }
    
    public static int getTipoUsuario(){
        return tipoUsuario;
    }
}
