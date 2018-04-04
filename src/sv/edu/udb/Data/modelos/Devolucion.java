/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.Data.modelos;

import java.math.BigDecimal;

/**
 *
 * @author Douglas
 */
public class Devolucion {
    public long id_devolucion;
    public long id_prestamo;
    public String fecha_devolucion;
    public BigDecimal mora_cancelada;
    public String descripcion;
    
    public long getId_devolucion(){
        return id_devolucion;
    }
    
    public long getId_prestamo(){
        return id_prestamo;
    }
    
    public String getFecha_devolucion(){
        return fecha_devolucion;
    }
    
    public BigDecimal getMora_cancelada(){
        return mora_cancelada;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    
}
