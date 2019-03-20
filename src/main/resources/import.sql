/*populate tables*/
INSERT INTO cliente (id,nombre,apellido,email,create_at,foto) VALUES (1,'Toni','Gonzalez Lopez','email@email.com','2018-02-20','');
INSERT INTO cliente (id,nombre,apellido,email,create_at,foto) VALUES (2,'Sofia','Rodriguez Perez','email2@email.com','2018-06-01','');
INSERT INTO cliente (id,nombre,apellido,email,create_at,foto) VALUES (3,'Jose','Gonzalez Lopez','email3@email.com','2018-03-20','');
INSERT INTO cliente (id,nombre,apellido,email,create_at,foto) VALUES (4,'Sonia','Rodriguez Perez','email4@email.com','2018-02-01','');
INSERT INTO cliente (id,nombre,apellido,email,create_at,foto) VALUES (5,'Marcelo','Gonzalez Lopez','email5@email.com','2018-12-20','');
INSERT INTO cliente (id,nombre,apellido,email,create_at,foto) VALUES (6,'Manuel','Rodriguez Perez','email6@email.com','2018-08-01','');
INSERT INTO cliente (id,nombre,apellido,email,create_at,foto) VALUES (7,'Alvaro','Gonzalez Lopez','email7@email.com','2018-02-22','');
INSERT INTO cliente (id,nombre,apellido,email,create_at,foto) VALUES (8,'Ana','Rodriguez Perez','email8@email.com','2018-06-01','');
INSERT INTO cliente (id,nombre,apellido,email,create_at,foto) VALUES (9,'Josefa','Gonzalez Lopez','email9@email.com','2018-02-20','');
INSERT INTO cliente (id,nombre,apellido,email,create_at,foto) VALUES (10,'Adela','Rodriguez Perez','email10@email.com','2018-06-01','');
INSERT INTO cliente (id,nombre,apellido,email,create_at,foto) VALUES (11,'Carlos','Gonzalez Lopez','email11@email.com','2018-02-20','');
INSERT INTO cliente (id,nombre,apellido,email,create_at,foto) VALUES (12,'Jusué','Rodriguez Perez','email12@email.com','2018-06-01','');
INSERT INTO cliente (id,nombre,apellido,email,create_at,foto) VALUES (13,'Marcos','Gonzalez Lopez','email13@email.com','2018-02-20','');
INSERT INTO cliente (id,nombre,apellido,email,create_at,foto) VALUES (14,'Lola','Rodriguez Perez','email14@email.com','2018-06-01','');
INSERT INTO cliente (id,nombre,apellido,email,create_at,foto) VALUES (15,'Lito','Gonzalez Lopez','email15@email.com','2018-02-20','');
INSERT INTO cliente (id,nombre,apellido,email,create_at,foto) VALUES (16,'Noelia','Rodriguez Perez','email16@email.com','2018-06-01','');
INSERT INTO cliente (id,nombre,apellido,email,create_at,foto) VALUES (17,'Desiree','Gonzalez Lopez','email17@email.com','2018-02-20','');
INSERT INTO cliente (id,nombre,apellido,email,create_at,foto) VALUES (18,'Jose Carlos','Rodriguez Perez','email18@email.com','2018-06-01','');
INSERT INTO cliente (id,nombre,apellido,email,create_at,foto) VALUES (19,'Chus','Gonzalez Lopez','email19@email.com','2018-02-20','');
INSERT INTO cliente (id,nombre,apellido,email,create_at,foto) VALUES (20,'Paz','Rodriguez Perez','email20@email.com','2018-06-01','');
INSERT INTO cliente (id,nombre,apellido,email,create_at,foto) VALUES (21,'Bea','Gonzalez Lopez','email21@email.com','2018-02-20','');
INSERT INTO cliente (id,nombre,apellido,email,create_at,foto) VALUES (22,'Susana','Rodriguez Perez','email22@email.com','2018-06-01','');


INSERT INTO productos (id, nombre, precio, create_at) VALUES (1,'television',3000,'2018-06-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES (2,'cafetera',250,'2018-08-01');
INSERT INTO productos (id, nombre, precio, create_at) VALUES (3,'licuadora',100,'2018-10-11');
INSERT INTO productos (id, nombre, precio, create_at) VALUES (4,'radio',30,'2018-07-09');
INSERT INTO productos (id, nombre, precio, create_at) VALUES (5,'ordenador',800,'2018-05-13');
INSERT INTO productos (id, nombre, precio, create_at) VALUES (6,'secador',300,'2018-04-15');
INSERT INTO productos (id, nombre, precio, create_at) VALUES (7,'batidora',50,'2018-02-20');

INSERT INTO facturas (id, descripcion, observacion, cliente_id, create_at) VALUES (1,'Factura informatica de Toni',null,1,'2018-02-20');
INSERT INTO factura_items (id, cantidad, factura_id, producto_id) VAlUES (1,1,1,1);
INSERT INTO factura_items (id, cantidad, factura_id, producto_id) VAlUES (2,2,1,4);
INSERT INTO factura_items (id, cantidad, factura_id, producto_id) VAlUES (3,4,1,5);

INSERT INTO facturas (id, descripcion, observacion, cliente_id, create_at) VALUES (2,'Factura de casa de Toni',null,1,'2018-02-20');
INSERT INTO factura_items (id, cantidad, factura_id, producto_id) VALUES (4,2,2,2);

INSERT INTO users (username, password, enabled) VALUES ('toni','$2a$10$lUnCInFu0mO79KaiOcct6e88TqKK/aQgfjKwY/x.VbcxkNyoyijrG',1);
INSERT INTO users (username, password, enabled) VALUES ('admin','$2a$10$.PKvmDPYMn8ukz3UxkTCDOKBQgLxR7EuvqEwl2xP/Gy/pREYTDtji',1);

INSERT INTO authorities (user_id, authority) VALUES (1, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (2, 'ROLE_USER');
INSERT INTO authorities (user_id, authority) VALUES (2, 'ROLE_ADMIN');