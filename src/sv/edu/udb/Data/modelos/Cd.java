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
public class Cd extends Item {
    public long id_cd;
    public String album;
    public String genero;
    public String fecha_lanzamiento;
    public String artista;
    
    public long getId_cd(){
        return id_cd;
    }
    
    public String getAlbum(){
        return album;
    }
    
    public String getGenero(){
        return genero;
    }
    
    public String getFecha_lanzamiento(){
        return fecha_lanzamiento;
    }
    
    public String getArtista(){
        return artista;
    }
}
