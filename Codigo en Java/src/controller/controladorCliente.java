package controller;

//Model
import model.cliente;
import model.pedido;
import model.operacionesDB;

//View
import view.registroCliente;
import view.crearPedidos;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;




public class controladorCliente implements ActionListener{

    cliente clienteC;
    pedido pedidoC;
    registroCliente vistaC;
    operacionesDB operacionesC;
    crearPedidos vistaPeC;
    

    public controladorCliente(cliente clienteC,pedido pedidoC, registroCliente vistaC, crearPedidos vistaPeC, operacionesDB operacionesC) {
        this.clienteC = clienteC;
        this.pedidoC = pedidoC;
        this.vistaC = vistaC;
        this.operacionesC = operacionesC;
        this.vistaPeC= vistaPeC;
        
        
        
        
        //Adding action listeners
        this.vistaC.btRegistrarCliente.addActionListener(this);
        this.vistaC.btBuscarCliente.addActionListener(this);
        this.vistaC.btLimpiar.addActionListener(this);
        this.vistaC.btHacerPedido.addActionListener(this);
    }
    
    
    public void iniciarVista(){
        
        vistaC.setTitle("Gestor- Registro");
        vistaC.setLocationRelativeTo(null);
    
    }
    
    public void iniciarVistap(){
        
        vistaPeC.setTitle("Pedidos");
        
    
    }
    
    
    public void limpiar(){
        
        vistaC.txtidCliente.setText(null);
        vistaC.txtNombre.setText(null);
        vistaC.txtDireccion.setText(null);
        vistaC.txtTelefono.setText(null);
        vistaC.txtCorreo.setText(null);
        
        vistaC.txtidCliente.requestFocus();
        
    }
   
    
   
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        //--------------INICIO botón registrarClient-------------------------
        if(ae.getSource() == vistaC.btRegistrarCliente){ 
            
            if(vistaC.txtidCliente.getText().equals("") ||
               vistaC.txtNombre.getText().equals("") ||
               vistaC.txtDireccion.getText().equals("") ||
               vistaC.txtTelefono.getText().equals("") ||
               vistaC.txtCorreo.getText().equals("")){
                
                JOptionPane.showMessageDialog(null, "Llena todos los campos");
            
            }else{
            
                clienteC.setCl_idCliente(vistaC.txtidCliente.getText());
                clienteC.setCl_nombre(vistaC.txtNombre.getText());
                clienteC.setCl_direccion(vistaC.txtDireccion.getText());
                clienteC.setCl_telefono(vistaC.txtTelefono.getText());
                clienteC.setCl_correo(vistaC.txtCorreo.getText());

                if(operacionesC.registrarCliente(clienteC)){

                    JOptionPane.showMessageDialog(null, "Cliente registardo");
                    limpiar();

                }else{

                    JOptionPane.showMessageDialog(null, "No se pudo registrar");
                    limpiar();

                }
            
            }
        
        }
        //--------------FIN botón registrarCliente-------------------------
        
        //--------------INICIO botón buscarCliente-------------------------
        if(ae.getSource() == vistaC.btBuscarCliente){
            
            clienteC.setCl_idCliente(vistaC.txtidCliente.getText());
            
            if(operacionesC.buscarCliente(clienteC)){
                
                vistaC.txtNombre.setText(clienteC.getCl_nombre());
                vistaC.txtDireccion.setText(clienteC.getCl_direccion());
                vistaC.txtTelefono.setText(clienteC.getCl_telefono());
                vistaC.txtCorreo.setText(clienteC.getCl_correo());
                
            
            }else{
                JOptionPane.showMessageDialog(null, "Cliente no registrado");
            }
        }
        //--------------FIN botón buscarCliente-------------------------
        
        //--------------INICIO botón limpiar-------------------------
        
        if(ae.getSource()== vistaC.btLimpiar){
            limpiar();
        }
        
        //--------------FIN botón limpiar-------------------------

        
        //--------------INICO botón hacerPedido-------------------------
        if(ae.getSource() == vistaC.btHacerPedido){
            
            iniciarVistap();
            vistaC.dispose();
            
            vistaPeC.setVisible(true);
            vistaPeC.setLocationRelativeTo(null);
            vistaPeC.txtIDcliente.setText(clienteC.getCl_idCliente());
            
            operacionesC.cargarCombosdesdeBD(vistaPeC.comboProductos);
            operacionesC.crearPedido(pedidoC, clienteC);
            operacionesC.consultarPedidoID(pedidoC);
            vistaPeC.txtIDpedido.setText( String.valueOf(pedidoC.getPedido_id()));
           
        }
        
        //--------------FIN botón hacerPedido-------------------------
    }
}
