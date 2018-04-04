/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sv.edu.udb.Data.modelos.Item;
import sv.edu.udb.Data.modelos.ItemPrestado;

/**
 *
 * @author Douglas
 */
public class ReportesService extends ServiceBase {

    public ReportesService() {
        super();
    }
    
    public List<ItemPrestado> itemsMasPrestados(){
        
        List<ItemPrestado> items = new ArrayList<ItemPrestado>();
        
        String query = "select i.nombre, i.descripcion, count(i.id_item) totalPrestado from item as i \n" +
            "inner join prestamo as p on p.id_item = i.id_item\n" +
            "group by i.nombre, i.descripcion\n" +
            "order by totalPrestado desc";
        
        ResultSet rs = conexion.RealizarQuery(query);
        
        try {
            while(rs.next()){
                ItemPrestado itemPrestado = new ItemPrestado();
                itemPrestado.nombre = rs.getString("nombre");
                itemPrestado.descripcion = rs.getString("descripcion");
                itemPrestado.cantidadPrestamos = rs.getInt("totalPrestado");
                items.add(itemPrestado);
            }
        } catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return items;
    }
    
    
}
