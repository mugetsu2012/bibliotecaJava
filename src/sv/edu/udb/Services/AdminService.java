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
import sv.edu.udb.Data.Conexion;
import sv.edu.udb.Data.modelos.DatosPersonales;
import sv.edu.udb.Data.modelos.Parametros;
import sv.edu.udb.Data.modelos.Rol;
import sv.edu.udb.Data.modelos.Usuario;

/**
 * Servico para todo el manejo administrativo, creacion/edicion de usuario
 * asignar roles, configurar parametros
 * @author Douglas
 */
public class AdminService extends ServiceBase {
    
    public AdminService(){
        super();
    }
    
    public void crearUsuario(Usuario usuario, DatosPersonales dp){
        dp.id_carne = usuario.id_carne;
        insertarUsuario(usuario);
        insertarDatosPersonales(dp);
    }
    
    private void insertarUsuario(Usuario usuario){
        String querySql = "insert into usuario(id_carne,id_catalogo_roles, password, estado) values('"+usuario.id_carne+"',"+usuario.id_catalogo_roles+",'"+usuario.passWord+"',1)";
        
        conexion.ejecutarQuery(querySql);       
    }
    
    /**
     * Metodo para editar usuario, por medio de su id_usuario, cambiamos la pass y su estado
     * @param usuario Objeto completo con el id_usuario y los demas datos a cambiar. <b>NO se cambia la columna usuario</b>
     */
    public void editarUsuario(Usuario usuario){
        String query = "update usuario\n" +
                "set password = '"+usuario.passWord+"',id_catalogo_roles="+usuario.id_catalogo_roles+",estado = "+usuario.estado+"\n" +
                "where id_carne = '"+usuario.id_carne+"'";
        
        conexion.ejecutarQuery(query);
    }
    
    private void insertarDatosPersonales(DatosPersonales dp){
        
        String query = "insert into datos_personales(id_carne,nombre,apellido,genero,email,telefono,direccion)\n" +
        "values ('"+dp.id_carne+"','"+dp.nombre+"','"+dp.apellido+"',"+dp.genero+",'"+dp.email+"',"
                + "'"+dp.telefono+"','"+dp.direccion+"')";
        
        conexion.ejecutarQuery(query);
    }
    
    public void editarDatosPersonales(DatosPersonales dp){
        String query = "update datos_personales\n" +
        "set  nombre='"+dp.nombre+"', apellido = '"+dp.apellido+"', genero = "+dp.genero+","
                + " email = '"+dp.email+"', telefono='"+dp.telefono+"', direccion='"+dp.direccion+"'\n" +
        "where id_carne = '"+dp.id_carne+"'";
        conexion.ejecutarQuery(query);
    }
    
    public void insertarParametros(Parametros parametros){
        String query = "insert into parametros(mora_por_dia,dias_prestar_alumno,dias_prestar_profesor,cantidad_prestar_alumno,cantidad_prestar_profesor)\n" +
            "values ("+parametros.mora_por_dia+","+parametros.dias_prestar_alumno+","+parametros.dias_prestar_profesor+",\n" +
            ""+parametros.cantidad_prestar_alumno+","+parametros.cantidad_prestar_profesor+")";
        
        conexion.ejecutarQuery(query);
    }
    
    public void editarParametros(Parametros parametros){
        
        String query = "update parametros\n" +
            "set mora_por_dia = "+parametros.mora_por_dia+", dias_prestar_alumno="+parametros.dias_prestar_alumno+",dias_prestar_profesor="+parametros.dias_prestar_profesor+", \n" +
            "cantidad_prestar_alumno = "+parametros.cantidad_prestar_alumno+", cantidad_prestar_profesor = "+parametros.cantidad_prestar_profesor+"\n" +
            "where id_parametros = "+parametros.id_parametros+"";
        
        conexion.ejecutarQuery(query);
    }
    
    public Parametros getParametros(){
        
        String query = "select * from parametros LIMIT 1";
        Parametros parametros = new Parametros();
        ResultSet rs = conexion.RealizarQuery(query);
        
        try{
            while(rs.next()){
                parametros.id_parametros = rs.getLong("id_parametros");
                parametros.mora_por_dia = rs.getBigDecimal("mora_por_dia");
                parametros.dias_prestar_alumno = rs.getInt("dias_prestar_alumno");
                parametros.dias_prestar_profesor = rs.getInt("dias_prestar_profesor");
                parametros.cantidad_prestar_alumno = rs.getInt("cantidad_prestar_alumno");
                parametros.cantidad_prestar_profesor = rs.getInt("cantidad_prestar_profesor");                
            }
        } catch(SQLException e){
            System.out.println("Error: " + e);
        }
        
        return parametros;
    }
    
    public List<Rol> getListaRoles(){
        
        List<Rol> roles = new ArrayList<Rol>();
        
        String query = "select * from catalogo_roles";
        
        ResultSet rs = conexion.RealizarQuery(query);
        
        try {
            while(rs.next()){
                Rol nuevoRol = new Rol();
                nuevoRol.id_catalogo_roles = rs.getLong("id_catalogo_roles");
                nuevoRol.rol = rs.getString("rol");
                nuevoRol.descripcion = rs.getString("descripcion");
                roles.add(nuevoRol);
            }
        } catch(SQLException e){
            System.out.println("Error: " + e.getMessage()); 
        }       
        
        return roles;
    }
    
    public Usuario getUsuario(String carne){
        
         Usuario user = new Usuario();
        
        String query = "select u.id_carne, u.id_catalogo_roles, u.password, u.estado, dp.nombre, dp.apellido,c.rol "
                + " from usuario as u"
                + " inner join datos_personales as dp on dp.id_carne = u.id_carne "
                + " inner join catalogo_roles as c on c.id_catalogo_roles = u.id_catalogo_roles "
                + " where u.id_carne = '"+carne+"'\n";
            
        
        ResultSet rs = conexion.RealizarQuery(query);
        
        try{
            while(rs.next()){
                
                user.id_carne = rs.getString("id_carne");
                user.id_catalogo_roles = rs.getLong("id_catalogo_roles");
                user.passWord = rs.getString("password");
                user.estado = rs.getInt("estado");
                user.nombre = rs.getString("nombre");
                user.apellido = rs.getString("apellido");
                user.nombreRol = rs.getString("rol");
                
            }
        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }               
                
        return user;
    }
    
    public DatosPersonales getDatosPersonales(String carne){
        String query = "select * from datos_personales where id_carne = '"+carne+"' limit 1";
        
        ResultSet rs = conexion.RealizarQuery(query);
        
        DatosPersonales dp = new DatosPersonales();
        
        try{
            while(rs.next()){
                dp.apellido = rs.getString("apellido");
                dp.direccion = rs.getString("direccion");
                dp.email = rs.getString("email");
                dp.genero = rs.getInt("genero");
                dp.id_carne = rs.getString("id_carne");
                dp.id_datos_personales = rs.getLong("id_datos_personales");
                dp.nombre = rs.getString("nombre");
                dp.telefono = rs.getString("telefono");
            }
        } catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return dp;
    }
    
    public List<Usuario> getListaUsuarios(String carne, long idRol, Integer estado){
        
        List<Usuario> usuarios = new ArrayList<Usuario>();
        
        String query = "select u.id_carne, u.id_catalogo_roles, u.password, u.estado, dp.nombre, dp.apellido,c.rol "
                + " from usuario as u"
                + " inner join datos_personales as dp on dp.id_carne = u.id_carne "
                + " inner join catalogo_roles as c on c.id_catalogo_roles = u.id_catalogo_roles "
                + " where (u.id_catalogo_roles = "+idRol+" or "+idRol+" = '0')\n" +
            "and (u.id_carne = '"+carne+"' or "+carne+" is null) and (u.estado = "+estado+" or "+estado+" is NULL)";
        
        ResultSet rs = conexion.RealizarQuery(query);
        
        try{
            while(rs.next()){
                Usuario user = new Usuario();
                user.id_carne = rs.getString("id_carne");
                user.id_catalogo_roles = rs.getLong("id_catalogo_roles");
                user.passWord = rs.getString("password");
                user.estado = rs.getInt("estado");
                user.nombre = rs.getString("nombre");
                user.apellido = rs.getString("apellido");
                user.nombreRol = rs.getString("rol");
                usuarios.add(user);
            }
        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
                
                
        return usuarios;
        
    }
    
    public boolean loginValido(String carne, String password){
        Usuario user = getUsuario(carne);
        
        //El usuario no existe
        if(user.id_carne == null){
            return false;
        }
        
        //Si la password no coinciden
        if(!user.passWord.equals(password)){
            return false;
        }
        
        return true;
    }

}
