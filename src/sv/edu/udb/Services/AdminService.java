/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import sv.edu.udb.Data.Conexion;
import sv.edu.udb.Data.modelos.Parametros;
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
    
    public long insertarUsuario(Usuario usuario){
        String querySql = "insert into usuario(usuario, password, estado) values('"+usuario.usuario+"','"+usuario.passWord+"',1)";
        
        long codigoUser = conexion.realizarInsert(querySql);
        
        return codigoUser;
        
    }
    
    /**
     * Metodo para editar usuario, por medio de su id_usuario, cambiamos la pass y su estado
     * @param usuario Objeto completo con el id_usuario y los demas datos a cambiar. <b>NO se cambia la columna usuario</b>
     */
    public void editarUsuario(Usuario usuario){
        String query = "update usuario\n" +
                "set password = '"+usuario.passWord+"',estado = "+usuario.estado+"\n" +
                "where id_usuario = "+usuario.id_usuario+"";
        
        conexion.ejecutarQuery(query);
    }
    
    public void asignarRolUsuario(String usuario, String rol){
        String query = "select * from usuario_roles where id_catalogo_roles = '"+rol+"' and id_usuario = '"+usuario+"'";
        ResultSet rs = conexion.RealizarQuery(query);
        try{
            //Si no hay ningun usuario con ese rol, lo insertamos
            if(!rs.isBeforeFirst()){
                query = "INSERT INTO usuario roles(id_catalogo_roles, id_usuario) values ('"+rol+"','"+usuario+"')";
                conexion.ejecutarQuery(query);
            }
        } catch(SQLException e){
            
        }      
        
    }
    
    public void removerRolUsuario(String usuario, String rol){
        String query = "delete from usuario_roles where id_catalogo_roles = '"+rol+"' and id_usuario = '"+usuario+"'";
        conexion.ejecutarQuery(query);          
    }
    
    public void editarParametros(Parametros parametros){
        
        String query = "update parametros\n" +
            "set mora_por_dia = "+parametros.mora_por_dia+", dias_prestar_alumno="+parametros.dias_prestar_alumno+",dias_prestar_profesor="+parametros.dias_prestar_profesor+", \n" +
            "cantidad_prestar_alumno = "+parametros.cantidad_prestar_alumno+", cantidad_prestar_profesor = "+parametros.cantidad_prestar_profesor+"\n" +
            "where id_parametros = "+parametros.id_parametros+"";
        
        conexion.ejecutarQuery(query);
    }
}
