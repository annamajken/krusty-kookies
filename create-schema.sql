-- The SQL statements needed to create the tables of database krusty --

SET FOREIGN_KEY_CHECKS = 0;
drop table if exists Ingredients;
drop table if exists Products;
drop table if exists Recipes;
drop table if exists Customers;
drop table if exists Orders;
drop table if exists Pallets;
drop table if exists OrderSpecs;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE Ingredients (
name varChar(30),
quantity int,
deliveryDate DATE,
deliveredAmount int,
unit varChar(10),
PRIMARY KEY (name)
);

CREATE TABLE Products (
productID int AUTO_INCREMENT,
productName varChar(30),
price int,
PRIMARY KEY (productID)
);

CREATE TABLE Recipes (
productID int,
ingredient varChar(30),
amount int,
PRIMARY KEY (productID, ingredient),
FOREIGN KEY (ingredient) REFERENCES Ingredients (name),
FOREIGN KEY (productID) REFERENCES Products (productID)
);

CREATE TABLE Customers (
customerID int AUTO_INCREMENT,
address varChar(60),
name varChar(40),
PRIMARY KEY (customerID)
);

CREATE TABLE Orders (
orderNbr int AUTO_INCREMENT,
customer int,
PRIMARY KEY (orderNbr),
FOREIGN KEY (customer) REFERENCES Customers (customerID)
);

CREATE TABLE Pallets (
palletNbr int AUTO_INCREMENT,
productID int,
dateAndTimeOfProduction DATETIME,
dateAndTimeOfDelivery DATETIME,
orderNbr int,
blocked boolean NOT NULL DEFAULT FALSE,
PRIMARY KEY (palletNbr),
FOREIGN KEY (productID) REFERENCES Products (productID),
FOREIGN KEY (orderNbr) REFERENCES Orders (orderNbr)
);

CREATE TABLE OrderSpecs (
orderNbr int,
productID int,
quantity int,
PRIMARY KEY (orderNbr, productID),
FOREIGN KEY (orderNbr) REFERENCES Orders (orderNbr),
FOREIGN KEY (productID) REFERENCES Products (productID)
);