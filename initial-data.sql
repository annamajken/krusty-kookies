-- Fills the tables with default data --

-- Inserts data into the Recipes table --

-- Inserts data into the Customers table --
INSERT INTO Customers(name, address) 
VALUES('Bjudkakor AB', 'Ystad'),
('Finkakor AB', 'Helsingborg'),
('Gästkakor AB', 'Hässleholm'),
('Kaffebröd AB', 'Landskrona'),
('Kalaskakor AB', 'Trelleborg'),
('Partykakor AB', 'Kristianstad'),
('Skånekakor AB', 'Perstorp'),
('Småbröd AB', 'Malmö');

-- Inserts data into the Products table --
INSERT INTO Products(productName)
VALUES('Almond delight'),
('Amneris'),
('Berliner'),
('Nut cookie'),
('Nut ring'),
('Tango');

-- Inserts data into the Ingredients table --
INSERT INTO Ingredients(name, quantity, unit)
VALUES('Bread crumbs', 500000, 'g'),
('Butter', 500000, 'g'),
('Chocolate', 500000, 'g'),
('Chopped almonds', 500000, 'g'),
('Cinnamon', 500000, 'g'),
('Egg whites', 500000, 'ml'),
('Eggs', 500000, 'g'),
('Fine-ground nuts', 500000, 'g'),
('Flour', 500000, 'g'),
('Ground, roasted nuts', 500000, 'g'),
('Icing sugar', 500000, 'g'),
('Marzipan', 500000, 'g'),
('Potato starch', 500000, 'g'),
('Roasted, chopped nuts', 500000, 'g'),
('Sodium bicarbonate', 500000, 'g'),
('Sugar', 500000, 'g'),
('Vanilla sugar', 500000, 'g'),
('Vanilla', 500000, 'g'),
('Wheat flour', 500000, 'g');

-- Almond Delight --
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(1, 'Butter', 400);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(1, 'Chopped almonds', 279);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(1, 'Cinnamon', 10);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(1, 'Flour', 400);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(1, 'Sugar', 270);

-- Amneris --
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(2, 'Butter', 250);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(2, 'Eggs', 250);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(2, 'Marzipan', 750);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(2, 'Potato starch', 25); 
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(2, 'Wheat flour', 25);

-- Berliner --
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(3, 'Butter', 250);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(3, 'Chocolate', 50);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(3, 'Eggs', 50);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(3, 'Flour', 350);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(3, 'Icing sugar', 100);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(3, 'Vanilla sugar', 5);

-- Nut Cookie --

INSERT INTO Recipes(productID, ingredient, amount)
VALUES(4, 'Bread crumbs', 125);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(4, 'Chocolate', 50);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(4, 'Egg whites', 350);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(4, 'Fine-ground nuts', 750);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(4, 'Ground, roasted nuts', 625);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(4, 'Sugar', 375);

-- Nut Ring --
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(5, 'Butter', 450);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(5, 'Flour', 450);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(5, 'Roasted, chopped nuts', 225);

-- Tango --
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(6, 'Butter', 200);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(6, 'Flour', 300);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(6, 'Sodium bicarbonate', 4);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(6, 'Sugar', 250);
INSERT INTO Recipes(productID, ingredient, amount)
VALUES(6, 'Vanilla', 2);
