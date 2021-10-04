package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import javax.swing.JComboBox;

import javax.swing.JOptionPane;


public class operacionesDB extends conexion {

    PreparedStatement ps;
    ResultSet rs;
    
    //----------------INICIO metodo registrarCliente --------------------------
    
    public boolean registrarCliente(cliente Cliente){
        
        Connection conexion = getConnection();
        
        try{
            
            ps = conexion.prepareStatement("insert into clientes (cl_idCliente, cl_nombre, cl_direccion, cl_telefono, cl_correo) values (?, ?, ?, ?, ?)");
            ps.setString(1, Cliente.getCl_idCliente());
            ps.setString(2, Cliente.getCl_nombre());
            ps.setString(3, Cliente.getCl_direccion());
            ps.setString(4, Cliente.getCl_telefono());
            ps.setString(5, Cliente.getCl_correo());
            
            int resultado= ps.executeUpdate();
            
            if(resultado>0){
                return true;
            }else{
                return false;
            }
        
        }catch(Exception e){
        
            JOptionPane.showMessageDialog(null, "Error: " + e);
            return false;
            
        }finally{
            try{
                conexion.close();
            }catch(Exception e){                  
                JOptionPane.showMessageDialog(null, "Error: " + e);
            }
        }
    }
    //----------------FINAL metodo registrarCliente --------------------------
    
    
    //----------------INICIO metodo buscarCliente --------------------------
        
    public boolean buscarCliente(cliente Cliente){
        
        Connection conexion = getConnection();
        
        try{
            
            ps = conexion.prepareStatement("select * from clientes where cl_idCliente = ?");
            ps.setString(1, Cliente.getCl_idCliente());
            rs = ps.executeQuery();
            
            if(rs.next()){
                
                Cliente.setCl_idCliente(rs.getString("cl_idCliente"));
                Cliente.setCl_nombre(rs.getString("cl_nombre"));
                Cliente.setCl_direccion(rs.getString("cl_direccion"));
                Cliente.setCl_telefono(rs.getString("cl_telefono"));
                Cliente.setCl_correo(rs.getString("cl_correo"));
                
                return true;
            }else{
                return false;
            }
        
        }catch(Exception e){
        
            JOptionPane.showMessageDialog(null, "Error: " + e);
            return false;
        }finally{
            try{
                conexion.close();
            }catch(Exception e){                  
                JOptionPane.showMessageDialog(null, "Error: " + e);
            }
        }
    }
    //----------------FIN metodo buscarCliente ---------------------------------
    
    
   
    
    //-------------------------INICIO operaciones pedido----------------------------    
    
    //Cargar combos desde la base de datos productos
    
    public boolean cargarCombosdesdeBD(JComboBox c){
        
        Connection conexion = getConnection();
        
        try{
            ps = conexion.prepareStatement("select prod_nombre from productos");
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                String name = rs.getString("prod_nombre");
                c.addItem(name);
             
            }
            
            return true;
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e);
            return false;
        }   
    }
    
    //------------------------------------------
    
   
    //  Considerar hacer una consulta del codigo del pedido
    
    //----------------INICIO metodo crearPedido --------------------------
    
    public boolean crearPedido(pedido Pedido, cliente Cliente){
    
        Connection conexion = getConnection();
        
        try{
            
            ps = conexion.prepareStatement("insert into pedidos (pedido_id, cliente_id) VALUES(?, ?)");  
            
            ps.setInt(1, Pedido.getPedido_id() );
            ps.setString(2, Cliente.getCl_idCliente());
           
            int resultado= ps.executeUpdate();
        

            
            if(resultado > 0){
              
             
              return true;
              
            
            }else{
                return false;
            }
            
        }catch(Exception e){
        
            JOptionPane.showMessageDialog(null, "Error up: " + e);
            return false;
            
        }finally{
            try {
                conexion.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Error :"+ex);
            }
        }
      
    }
    
    //----------------FIN metodo crearPedido --------------------------
    
    
   //----------------INICO metodo consultarPedidoID (auto_increment) -----------
    
    public boolean consultarPedidoID(pedido Pedido){
    
    Connection conexion = getConnection();
    
    try{
            //SELECT AUTO_INCREMENT FROM information_schema. TABLES WHERE TABLE_SCHEMA = "gestor" AND TABLE_NAME = "pedidos"
            ps = conexion.prepareStatement("select auto_increment from information_schema. tables where table_schema = 'gestor' and table_name= 'pedidos'");  
            rs= ps.executeQuery();
            
            if(rs.next()){
                
                int pedidoId = rs.getInt("AUTO_INCREMENT") - 1;  
                Pedido.setPedido_id(pedidoId);
                
            }
        
            return true;

            
        }catch(Exception e){
        
            JOptionPane.showMessageDialog(null, "Error up: " + e);
            return false;
            
        }finally{
            try {
                conexion.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Error :"+ex);
            }
        } 
    }
   //----------------FIN metodo consultarPedidoID (auto_increment) -------------
    
   //----------------INICIO metodo consultarProductos --------------------------
    
    public boolean consultarProductos(JComboBox combo, producto Producto){
    
        Connection conexion = getConnection();
        
        try{
            String productoCom = combo.getSelectedItem().toString();
            
            ps = conexion.prepareStatement("select * from productos where prod_nombre = (?)");
            ps.setString(1, productoCom);
            rs= ps.executeQuery();
            
            if(rs.next()){
                
                Producto.setProd_id(rs.getInt("prod_id"));
                Producto.setProd_nombre(rs.getString("prod_nombre"));
                Producto.setProd_precio(rs.getDouble("prod_precio"));
           
            }
           return true;
           
           
        }catch(Exception e){
        
            JOptionPane.showMessageDialog(null, "Error up: " + e);
            return false;
            
        }finally{
            try {
                conexion.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Error :"+ex);
            }
        } 
        
    }
    //----------------FIN metodo consultarProductos -----------------------------
    
    
    
    //----------------INICIO metodo añadirProducto --------------------------
    
    public boolean añadirProducto(producto Producto, pedido Pedido){
    
        Connection conexion = getConnection();
        
        try{
            int idPedido = Pedido.getPedido_id();
            int idProducto = Producto.getProd_id();
            
            ps = conexion.prepareStatement("INSERT INTO itempedidoprod (producto_idF, pedido_idF) VALUES(?, ?)");
            
            ps.setInt(1, idProducto);
            ps.setInt(2, idPedido);
            ps.executeUpdate();
            
           return true;
           
           
        }catch(Exception e){
        
            JOptionPane.showMessageDialog(null, "Error up: " + e);
            return false;
            
        }finally{
            try {
                conexion.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Error :"+ex);
            }
        } 
    }
    //----------------FIN metodo añadirProducto -----------------------------
    
   
}