CREATE DATABASE Gestor;
USE Gestor;
//-------------------------------
CREATE TABLE clientes
(
	cl_idCliente VARCHAR(10),
    cl_nombre VARCHAR(15),
    cl_direccion VARCHAR(25),
    cl_telefono VARCHAR(10),
    cl_correo VARCHAR(20),
    
    PRIMARY KEY(cl_idCliente)
    
);
//-------------------------------
CREATE TABLE productos
(
	prod_id INT(3),
    prod_nombre VARCHAR(25),
    prod_precio DOUBLE,

	PRIMARY KEY(prod_id)
);

//-------------------------------
CREATE TABLE pedidos
(
	pedido_id INT NOT NULL AUTO_INCREMENT,
    cliente_id VARCHAR(10),
    
    PRIMARY KEY (pedido_id),
    FOREIGN KEY (cliente_id) REFERENCES clientes (cl_idCliente)
);

//--------------------------
CREATE TABLE itemPedidoProd
(
	item_id INT AUTO_INCREMENT,
    producto_idF INT NOT NULL,
    pedido_idF INT NOT NULL,
    
    PRIMARY KEY (item_id),
    CONSTRAINT FK_ped FOREIGN KEY (pedido_idF)
    REFERENCES pedidos(pedido_id),
    CONSTRAINT FK_prod FOREIGN KEY (producto_idF)
    REFERENCES productos(prod_id)
);
//--------------------------

//------------------------------------------------------------------

/*SOME QUERIES*/
SELECT AUTO_INCREMENT FROM information_schema. TABLES WHERE TABLE_SCHEMA = "gestor" AND TABLE_NAME = "pedidos";


SELECT * FROM productos;
SELECT * FROM clientes;
SELECT * FROM pedidos;
SELECT * FROM itemPedidoProd;


INSERT INTO itempedidoprod (producto_idF, pedido_idF) VALUES();

SELECT producto_idF FROM itempedidoprod WHERE pedido_idF = 95;

