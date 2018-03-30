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
public class Parametros {
    public long id_parametros;
    public BigDecimal mora_por_dia;
    public int dias_prestar_profesor;
    public int dias_prestar_alumno;
    public int cantidad_prestar_alumno;
    public int cantidad_prestar_profesor;
    
    public long getId_parametros(){
        return id_parametros;
    }
    
    public BigDecimal getMora_por_dia(){
        return mora_por_dia;
    }
    
    public int getDias_prestar_profesor(){
        return dias_prestar_profesor;
    }
    
    public int getDias_prestar_alumno(){
        return dias_prestar_profesor;
    }
    
    public int getCantidad_prestar_alumno(){
        return cantidad_prestar_alumno;
    }
    
    public int getCantidad_prestar_profesor(){
        return cantidad_prestar_profesor;
    }    
}
