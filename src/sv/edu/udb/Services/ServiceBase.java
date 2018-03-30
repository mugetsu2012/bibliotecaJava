/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.Services;

import java.sql.SQLException;
import sv.edu.udb.Data.Conexion;

/**
 *
 * @author Douglas
 */
public abstract class ServiceBase {
    Conexion conexion = null;
    
    public ServiceBase(){
        try
        {
            conexion = new Conexion();
        }
        catch(SQLException e){
            System.out.println("ERROR:No se pudo construir el objeto de conexion:" + e.getMessage());
        }
    }
}
