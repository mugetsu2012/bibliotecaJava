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
public class Tesis extends Item {
    public long id_tesis;
    public String fecha_publicacion;
    public String lugar_desarrollo;
    public String autores;
    
    public long getId_tesis(){
        return id_tesis;
    }
    
    public String getFecha_publicacion(){
        return fecha_publicacion;
    }
    
    public String getLugar_desarrollo(){
        return lugar_desarrollo;
    }
    
    public String getAutores(){
        return autores;
    }   
}
