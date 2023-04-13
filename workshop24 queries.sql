use northwind;

drop table fruits_products;
drop table purchase_order_details;
drop table purchase_order;

CREATE TABLE purchase_order (
    order_id INT AUTO_INCREMENT NOT NULL,
    order_date DATE NOT NULL,
    customer_name VARCHAR(128) NOT NULL,
    ship_address VARCHAR(128),
    notes LONGTEXT,
    tax DECIMAL(2,2) DEFAULT 0.05,
    PRIMARY KEY (order_id)
)auto_increment = 1001 ;

CREATE TABLE purchase_order_details (
  id INT AUTO_INCREMENT NOT NULL,
  product VARCHAR(64),
  unit_price DECIMAL(5,2),
  discount DECIMAL(3,2) DEFAULT 0.95,
  quantity INT,
  order_id INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_order_id
    FOREIGN KEY (order_id) REFERENCES purchase_order(order_id)
);



CREATE TABLE fruits_products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    standard_price DECIMAL(10, 2) NOT NULL,
    discount DECIMAL(3, 2) DEFAULT 1.0
);

INSERT INTO fruits_products (name, standard_price, discount) VALUES
   ('Apple', 5.0, 0.9),
   ('Banana', 5.99, 0.85),
   ('Orange', 4.49, 0.85),
   ('Pear', 6.79, 0.85),
   ('Pineapple', 8.99, 0.9),
   ('Grapes', 8.99, 0.7),
   ('Watermelon', 7.99, 0.8),
   ('Strawberry', 7.99, 1.0),
   ('Blueberry', 8.99, 1.0),
   ('Kiwi', 4.99, 0.95);

SELECT SYSDATE();
SELECT DATE_FORMAT(DATE(SYSDATE()), '%Y-%m-%d');

-- PURCHASE ORDER TABLE
insert into purchase_order(order_date, customer_name, ship_address, notes)
values
( DATE_FORMAT(DATE(SYSDATE()), '%Y-%m-%d'),'happpy','amk','doorstep');

-- PURCHASE ORDER DETAILS TABLE
insert into purchase_order_details(product, unit_price, discount, quantity, order_id)
values
('Apple', 5.0, 1.0, 3, 1004);

select tax from purchase_order where order_id = 1001;

select * from fruits_products;
select * from purchase_order;
select * from purchase_order_details;

select po.order_id as orderId, 
po.order_date as orderDate, 
po.customer_name as customerName, 
po.ship_address as shipAddress, 
pod.product as product,
pod.unit_price as unitPrice,
pod.discount as discount,
pod.quantity as quantity
from purchase_order po join purchase_order_details pod on po.order_id = pod.order_id
where po.order_id = 1001;
 





