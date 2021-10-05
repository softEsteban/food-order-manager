package mainOne;

import controller.controladorCliente;
import controller.controladorPedido;

import model.operacionesDB;
import model.cliente;
import model.pedido;
import model.producto;

import view.registroCliente;
import view.crearPedidos;
import view.factura;

public class mainGestor {

    public static void main(String[] args) {
        
        //-----------------------------------------------------------------------------
        
        
        //Modelo
        cliente clienteM = new cliente();
        pedido pedidoM = new pedido();  
        producto productoM = new producto();
        
        operacionesDB operaciones = new operacionesDB();
        
        //Vista
        registroCliente vistaRegistro = new registroCliente();
        crearPedidos vistaPedidos = new crearPedidos();
        factura facturaVista = new factura();
        
        //Controladores
        controladorCliente controlador = new controladorCliente(clienteM, pedidoM, vistaRegistro, vistaPedidos, operaciones);
        controladorPedido controladorP = new controladorPedido(clienteM, vistaPedidos, facturaVista, pedidoM, productoM, operaciones);
        
        controladorP.iniciarTabla();
        controladorP.iniciarTablaFact();
        controlador.iniciarVista();
        vistaRegistro.setVisible(true);
      
        
    }    
}
