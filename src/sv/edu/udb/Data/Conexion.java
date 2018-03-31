/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.Data;


import java.sql.*;

/**
 *
 * @author Douglas
 */
public class Conexion {

    private Connection conexion = null;
    private Statement s = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String query = "";

    public Conexion() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecajava", "root", "pass1234");
            s = conexion.createStatement();
        } catch (ClassNotFoundException e1) {
            System.out.println("ERROR:No encuentro el driver de la BD:" + e1.getMessage());
        }
    }   

    /**
     * Metodo que se utiliza solo para hacer consultas , es decir, selects
     */
    public ResultSet RealizarQuery(String consulta) {
        try {
            this.rs = s.executeQuery(consulta);
            return rs;
        } catch (SQLException e2) {
            System.out.println("ERROR: Fallo en SQL: " + e2.getMessage());
        }
        
        return null;
    }

    /**
     * Se utiliza para realizar Updates, Deletes e <b>Insers que NO tienen tablas con identity</b>
     */
    public void ejecutarQuery(String query) {
        
        try{
            this.s.executeUpdate(query);
        } catch(SQLException e){
            System.out.println("Error al ejecutar un query :" + e.getMessage());
        }
             
    }
    
    /**
     * Metodo que inserta en la base por medio de los comandos SQL, devuelve el ID del objeto insertado
     * <b>Solo debe usarse si la tabla tiene una llave primaria con identity</b>
     */
    public long realizarInsert(String query){
        
        long id = 0;
        try{
            ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            
        } catch(SQLException e){
            System.out.println("Error al insertar un dato :" + e.getMessage());
        }
        
        try{
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getLong(1);
            }
        }
        catch(SQLException se){
            System.out.println("Error al insertar un dato :" + se.getMessage());
        }
        
        
        return id;
        
    }

    //Cierra la conexion
    public void cerrarConexion() {
        try{
                 if (ps != null) ps.close();
                if (rs != null) rs.close();
                if(conexion != null) conexion.close();
            }
             catch(Exception e){
                 System.out.println("Error fatal al cerrar la conexion: "+ e.getMessage());
             }
    }
}
