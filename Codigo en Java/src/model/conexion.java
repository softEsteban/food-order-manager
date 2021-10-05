package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import javax.swing.JOptionPane;


public class conexion {

    final String url = "jdbc:mysql://localhost:3306/gestor";
    final String usuario = "root";
    final String password= "root";
    final String Driver= "com.mysql.jdbc.Driver";
    
    PreparedStatement ps;
    ResultSet rs;
    
    //Crear conexiones
    public Connection getConnection(){
    
        Connection conexionX = null;
        
        try{
            Class.forName(Driver);
            conexionX = (Connection) DriverManager.getConnection(url, usuario, password);
            
            
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, "Error: " + e);
            
        }
    
        return conexionX;
    }

    
    
}
