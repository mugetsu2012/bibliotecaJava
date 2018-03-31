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
    
    public void insertarUsuario(Usuario usuario){
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
                "where id_carne = "+usuario.id_carne+"";
        
        conexion.ejecutarQuery(query);
    }
    
    public void editarParametros(Parametros parametros){
        
        String query = "update parametros\n" +
            "set mora_por_dia = "+parametros.mora_por_dia+", dias_prestar_alumno="+parametros.dias_prestar_alumno+",dias_prestar_profesor="+parametros.dias_prestar_profesor+", \n" +
            "cantidad_prestar_alumno = "+parametros.cantidad_prestar_alumno+", cantidad_prestar_profesor = "+parametros.cantidad_prestar_profesor+"\n" +
            "where id_parametros = "+parametros.id_parametros+"";
        
        conexion.ejecutarQuery(query);
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
        
        String query = "select top 1 * from usuario where id_carne = '"+carne+"'";
        
        ResultSet rs = conexion.RealizarQuery(query);
        
        Usuario user = new Usuario();
        
        try{
            while(rs.next()){
                
                user.id_carne = rs.getString("id_carne");
                user.id_catalogo_roles = rs.getLong("id_catalogo_roles");
                user.passWord = rs.getString("password");
                user.estado = rs.getInt("estado");
                
            }
        } catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }      
        
        return user;
        
    }
    
    public List<Usuario> getListaUsuarios(String carne, long idRol, Integer estado){
        
        List<Usuario> usuarios = new ArrayList<Usuario>();
        
        String query = "select * from usuario where (id_catalogo_roles = "+idRol+" or "+idRol+" = '0')\n" +
            "and (id_carne = '"+carne+"' or '"+carne+"' is null) and (estado = "+estado+" or "+estado+" is NULL)";
        
        ResultSet rs = conexion.RealizarQuery(query);
        
        try{
            while(rs.next()){
                Usuario user = new Usuario();
                user.id_carne = rs.getString("id_carne");
                user.id_catalogo_roles = rs.getLong("id_catalogo_roles");
                user.passWord = rs.getString("password");
                user.estado = rs.getInt("estado");
                usuarios.add(user);
            }
        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
                
                
        return usuarios;
        
    }
}
