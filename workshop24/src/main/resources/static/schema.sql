create database if not exists trial;

use trial;

create table if not exists products (
 id INT PRIMARY KEY,
 standard_cost DECIMAL(10,2)
);