/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.Services;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import sv.edu.udb.Data.modelos.Devolucion;
import sv.edu.udb.Data.modelos.Item;
import sv.edu.udb.Data.modelos.Parametros;
import sv.edu.udb.Data.modelos.Prestamo;
import sv.edu.udb.Data.modelos.Usuario;

/**
 *
 * @author Douglas
 */
public class PrestamosService extends ServiceBase {
    
    public PrestamosService(){
        super();
    }
    
    public List<Prestamo> getListaPrestamo(){
        
        List<Prestamo> prestamos = new ArrayList<Prestamo>();
        
        String query = "select * from prestamo left join devolucion "
                + "on devolucion.id_prestamo = prestamo.id_prestamo where devolucion.id_prestamo is null";
        
        ResultSet rs = conexion.RealizarQuery(query);
        
        try{
            while(rs.next()){
                Prestamo prestamo = new Prestamo();
                prestamo.id_prestamo = rs.getLong("id_prestamo");
                prestamo.id_item = rs.getLong("id_item");
                prestamo.id_carne_autoriza = rs.getString("id_carne_prestamista");
                prestamo.id_carne_solicita = rs.getString("id_carne_solicitante");
                prestamo.fecha_pactada = rs.getString("fecha_pactada");
                prestamo.fecha_prestamo = rs.getString("fecha_prestamo");
                prestamo.descripcion = rs.getString("descripcion");
                prestamos.add(prestamo);
                
            }
        } catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return prestamos;
    }
    
    Prestamo getPrestamo(long codigoPrestamo){
        Prestamo prestamo = new Prestamo();
        
        String query = "select * from prestamo where id_prestamo = "+codigoPrestamo+"";
        
        ResultSet rs = conexion.RealizarQuery(query);
        
        try{
            while(rs.next()){
                
                prestamo.id_prestamo = rs.getLong("id_prestamo");
                prestamo.id_item = rs.getLong("id_item");
                prestamo.id_carne_autoriza = rs.getString("id_carne_prestamista");
                prestamo.id_carne_solicita = rs.getString("id_carne_solicitante");
                prestamo.fecha_pactada = rs.getString("fecha_pactada");
                prestamo.fecha_prestamo = rs.getString("fecha_prestamo");
                prestamo.descripcion = rs.getString("descripcion");
                
                
            }
        } catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return prestamo;
    }
    
    public List<Devolucion> getListaDevolucion(){
        List<Devolucion> devoluciones = new ArrayList<Devolucion>();
        
        String query = "select * from devolucion";
        
        ResultSet rs = conexion.RealizarQuery(query);
        
        try{
            while(rs.next()){
                Devolucion devolucion = new Devolucion();
                devolucion.id_devolucion = rs.getLong("id_devolucion");
                devolucion.id_prestamo=rs.getLong("id_prestamo");
                devolucion.fecha_devolucion=rs.getString("fecha_devolucion");
                devolucion.mora_cancelada=rs.getBigDecimal("mora_cancelada");
                devolucion.descripcion=rs.getString("descripcion");
            }
        } catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return devoluciones;
    }
    
    /**
     * Metodo que ejecuta un prestamo, siempre y cuando el usuario no tenga mora
     * @param prestamo El objeto prestamo con todos los datos menos la fecha pactada
     * @return La fecha en la que debe ser devuelto el item
     */
    public String realizarPrestamo(Prestamo prestamo){
        
        String fechaPactada = "";      
        
        boolean usurioPuedePrestar = usuarioPuedeRealizarPrestamo(prestamo.id_carne_solicita);
        boolean itemEstaDisponible = itemDisponibleParaPrestamo(prestamo.id_item);
        
        //Verificamos que el usuario cumpla las validaciones de prestamos
        if(usurioPuedePrestar && itemEstaDisponible){
            
            //Entra acá al no tener mora
            fechaPactada = calcularFechaPactada(prestamo.id_carne_solicita);
            
            //Preocedemos a calcular la fecha pactada en base al tipo de usuario
            String query = "insert into prestamo(id_item, id_carne_prestamista,id_carne_solicitante,fecha_prestamo,fecha_pactada,descripcion) values("+prestamo.id_item+",'"+prestamo.id_carne_autoriza+"',"
                + "'"+prestamo.id_carne_solicita+"','"+getFechaHoraActual()+"','"+fechaPactada+"','"+prestamo.descripcion+"')";
            
            conexion.ejecutarQuery(query);
        }       
        
        return fechaPactada;
    }
    
    
    /**
     * Metodo para llevar a cabo una devolucion, <b>OJO: Si tiene que cancenlar mora al realizar la devolucion y no viene dicho dato, no se realizar el proceso</b>
     * @param devolucion Objeto devolucion sin la fecha y con la mora si es requerido
     */
    public void realizarDevolucion(Devolucion devolucion){
        boolean prestamoEnMora = prestamoEnMora(devolucion.id_prestamo);
        if(prestamoEnMora){
            
        }
        else {
            //Como no está en mora, solo hacemos el insert normalmente
            String query = "insert into devolucion (id_prestamo, fecha_devolucion,mora_cancelada,descripción)\n" +
                "values ("+devolucion.id_prestamo+",'"+getFechaHoraActual()+"',null,"+devolucion.descripcion+")";
            conexion.ejecutarQuery(query);
        }
    }
    
    public boolean usuarioPuedeRealizarPrestamo(String carne){
                
        //Mando a ver si el usuario tiene mora
        boolean usuarioTieneMora= usuarioTieneMora(carne);
        
        
        //Si tiene mora, no se le presta
        if(usuarioTieneMora){
            return false;
        }
        
        //Si ya tiene el limite de items prestados, no se presta
        int totalPrestamosActivos = totalPrestamosActivosUsuario(carne);
               
        //Sacamos al usuario, para ver su rol        
        AdminService adminService = new AdminService();
        Usuario user = adminService.getUsuario(carne);
        
        //Saco los parametros
        Parametros parametros = adminService.getParametros();
        
        //Sacamos el limite con base en si es alumno o profesor
        int limite = user.id_catalogo_roles == 2 ? parametros.cantidad_prestar_alumno : parametros.cantidad_prestar_profesor;
        
        //Si ya tiene prestados una cantidad de items igual o superior al limte
        if(totalPrestamosActivos >= limite){
            return false;
        }
        
        //Si pasa la validacion de no tener mora y no tener mas libros prestados de la cuenta, tonces true
        return true;
        
    }
    
    public boolean usuarioTieneMora(String carne){
        
        String query = "select count(*) total from prestamo as p\n" +
            "left join devolucion as d on d.id_prestamo = p.id_prestamo\n" +
            "where p.id_carne_solicitante = '"+carne+"'\n" +
            "and '"+getFechaHoraActual()+"' > p.fecha_pactada and d.id_devolucion is null";
        
        ResultSet rs = conexion.RealizarQuery(query);
        int prestamosEnMora = 0;
        
        try{
            while(rs.next()){
                prestamosEnMora = rs.getInt("total");
            }
        } catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return prestamosEnMora > 0;        
    }
    
    public boolean prestamoEnMora(long codigoPrestamo){
        String query = "select count(*) as total from prestamo as p\n" +
        "left join devolucion as d on d.id_prestamo = p.id_prestamo\n" +
        "where '"+getFechaHoraActual()+"' > p.fecha_pactada and d.id_devolucion is null\n" +
        "and p.id_prestamo = "+codigoPrestamo+"";
        
        ResultSet rs = conexion.RealizarQuery(query);
        int conteo = Integer.MAX_VALUE;
        
        try{
            while(rs.next()){
                conteo = rs.getInt("total");
            }
        } catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return conteo > 0;   
    }
    
    public BigDecimal totalMora(long codigoPrestamo){
        BigDecimal totalMora = BigDecimal.ZERO;
        AdminService adminService = new AdminService();
        Prestamo prestamo = getPrestamo(codigoPrestamo);
        
        //Fomato que utilizamos
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaPactada = prestamo.fecha_pactada;
        String fechaActual = getFechaHoraActual();

        try {
            
            //Se crea un date para cada fecha
            Date datePactada = formato.parse(fechaPactada);
            Date dateActual = formato.parse(fechaActual);
            
            //Se saca la diferencia en dias
            long diff = dateActual.getTime() - datePactada.getTime();
            long diasRetraso = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            
            //Vamos a leer a como esta el dia
            Parametros parametros = adminService.getParametros();
            
            totalMora = parametros.mora_por_dia.multiply(new BigDecimal(diasRetraso));
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return totalMora;
    }
    
    public int totalPrestamosActivosUsuario(String carne){
        
        String query = "select count(*) as totalActivos  from prestamo  as p\n" +
            "left join devolucion as d on d.id_prestamo = p.id_prestamo\n" +
            "where id_carne_solicitante = 'op130045' and d.id_devolucion is null";
        
        ResultSet rs = conexion.RealizarQuery(query);
        int totalPrestamosActivos = 0;
        
        try{
            while(rs.next()){
                totalPrestamosActivos = rs.getInt("totalActivos");
            }
        } catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return totalPrestamosActivos;
    }
    
    public boolean itemDisponibleParaPrestamo(long codigoItem){
        
        String query = "select count(*) as total from prestamo as p\n" +
            "left join devolucion as d on d.id_prestamo = p.id_prestamo\n" +
            "where id_item = "+codigoItem+" and d.id_devolucion is null";
        
        int unidadesPrestadas = Integer.MAX_VALUE;
        ResultSet rs = conexion.RealizarQuery(query);
        
        ItemsService itemsService = new ItemsService();
        
        Item item = itemsService.getItem(codigoItem);
        
        try{
            rs.next();
            unidadesPrestadas = rs.getInt("total");
        } catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return unidadesPrestadas < item.unidades_para_prestar;
    }
    
    private String calcularFechaPactada(String carne){
        
        AdminService adminService = new AdminService();
        
        //Sacamos al usuario, para ver su rol
        Usuario user = adminService.getUsuario(carne);
        
        //Saco los parametros
        Parametros parametros = adminService.getParametros();
        
        
        String fechaHoy = getFechaHoraActual();  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        try{
             c.setTime(sdf.parse(fechaHoy));
        } catch(Exception e){
            System.out.println("Error: "+ e.getMessage());
        }
        
        //Si es alumno
        if(user.id_catalogo_roles == 2){
            
            c.add(Calendar.DATE, parametros.dias_prestar_alumno);  
            
        }else {
            //Si es admin o profesor
            c.add(Calendar.DATE, parametros.dias_prestar_profesor); 
        }
        
        fechaHoy = sdf.format(c.getTime());  
        
        return fechaHoy;
    }
}
