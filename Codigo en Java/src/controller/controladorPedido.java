package controller;

import model.operacionesDB;
import model.pedido;
import model.producto;
import model.cliente;

import view.crearPedidos;
import view.factura;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import javax.swing.JOptionPane;


public class controladorPedido implements ActionListener{

    cliente clienteCon;
    
    crearPedidos vistaPedidosC;
    factura facturaCon;
    pedido pedidoC;
    producto productoC;
    operacionesDB operacionesCPed;
    
    DefaultTableModel tablaModelo;
    DefaultTableModel tablaModelo2;
    
    public controladorPedido(cliente clienteCon, crearPedidos vistaPedidosC, factura facturaCon, pedido pedidoC,producto productoC, operacionesDB operacionesCPed) {
        this.clienteCon= clienteCon;
        this.vistaPedidosC = vistaPedidosC;
        this.facturaCon =facturaCon;
        this.pedidoC = pedidoC;
        this.operacionesCPed =operacionesCPed;
        this.productoC = productoC;
        
        this.vistaPedidosC.btAñadirProducto.addActionListener(this);
        this.vistaPedidosC.btFacturar.addActionListener(this);
        this.facturaCon.btCerrar.addActionListener(this);
        this.facturaCon.btGuardar.addActionListener(this);
        this.facturaCon.btImprimer.addActionListener(this);
    }
    
    
    public void iniciarTabla(){
        
        tablaModelo=new DefaultTableModel();
        tablaModelo.addColumn("Producto");
        tablaModelo.addColumn("Precio");
        vistaPedidosC.tablaS.setModel(tablaModelo);
    }
    
    public void iniciarTablaFact(){
        
        tablaModelo2=new DefaultTableModel();
        tablaModelo2.addColumn("Codigo");
        tablaModelo2.addColumn("Producto");
        tablaModelo2.addColumn("Precio");
        facturaCon.tablaFact.setModel(tablaModelo2);
    }
    
    public void iniciarFactura(){
    
        facturaCon.setLocationRelativeTo(null);
        facturaCon.setTitle("Facturación");
        facturaCon.setVisible(true);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        //--------------INICIO botón añadirProducto ----------------------
        if(ae.getSource()== vistaPedidosC.btAñadirProducto){
            
            operacionesCPed.consultarProductos(vistaPedidosC.comboProductos, productoC);
            operacionesCPed.añadirProducto(productoC, pedidoC);
            
            
            
            String datosTabla[] = new String[2];
            datosTabla[0] = productoC.getProd_nombre();
            datosTabla[1] = String.valueOf(productoC.getProd_precio());
            
            tablaModelo.addRow(datosTabla);
            
            
            String datosTabla2[] = new String[3];
            datosTabla2[0] = String.valueOf(productoC.getProd_id());
            datosTabla2[1] = productoC.getProd_nombre();
            datosTabla2[2] = String.valueOf(productoC.getProd_precio());
            
            tablaModelo2.addRow(datosTabla2);
            
        }
        //--------------FIN botón añadirProducto -------------------------
        
        //--------------INICIO botón facturar -------------------------
        
        if(ae.getSource()== vistaPedidosC.btFacturar){
        
            this.iniciarFactura();
            
            
            facturaCon.factTxtCliente.setText(clienteCon.getCl_idCliente());
            facturaCon.factTxtPedido.setText(String.valueOf(pedidoC.getPedido_id()));
            
            double p = 0;
            double total = 0;
            
            for (int i = 0; i < facturaCon.tablaFact.getRowCount(); i++) {
                
                p = Double.parseDouble(facturaCon.tablaFact.getValueAt(i, 2).toString());
                total = total +  p;
                
            }
            
            facturaCon.txtTotalPago.setText(String.valueOf(total));
        
        }
        //--------------FIN botón facturar -------------------------

        //--------------INICIO botón cerrarFactura -------------------------
        if(ae.getSource()== facturaCon.btCerrar){
            
            facturaCon.dispose();
        
        }
        //--------------FIN botón cerrarFactura -------------------------

        
        if(ae.getSource()== facturaCon.btGuardar){
            JOptionPane.showMessageDialog(null, "Guardado");
        }
        
        if(ae.getSource()== facturaCon.btImprimer){
            JOptionPane.showMessageDialog(null, "Imprimiendo");
        }
        
        
    }
}
