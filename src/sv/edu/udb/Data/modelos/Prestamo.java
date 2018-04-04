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
public class Prestamo {
    
    public long id_prestamo;
    public long id_item;
    public String id_carne_autoriza;
    public String id_carne_solicita;
    public String fecha_prestamo;
    public String fecha_pactada;
    public String descripcion;
    
    public long getId_prestamo(){
        return id_prestamo;
    }
    public long getId_item(){
        return id_item;
    }
    
    public String getId_carne_autoriza(){
        return id_carne_autoriza;
    }
    
    public String getId_carne_solicita(){
        return id_carne_solicita;
    }
    
    public String getFecha_prestamo(){
        return fecha_prestamo;
    }
    
    public String getFecha_pactada(){
        return fecha_pactada;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    
}
