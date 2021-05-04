-- Fills the tables with default data --


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

-- Inserts data into the Recipes table --

-- Almond Delight --
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Almond Delight', 'Butter', 400);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Almond Delight', 'Chopped almonds', 279);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Almond Delight', 'Cinnamon', 10);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Almond Delight', 'Flour', 400);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Almond Delight', 'Sugar', 270);

-- Amneris --
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Amneris', 'Butter', 250);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Amneris', 'Eggs', 250);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Amneris', 'Marzipan', 750);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Amneris', 'Potato starch', 25); 
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Amneris', 'Wheat flour', 25);

-- Berliner --
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Berliner', 'Butter', 250);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Berliner', 'Chocolate', 50);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Berliner', 'Eggs', 50);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Berliner', 'Flour', 350);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Berliner', 'Icing sugar', 100);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Berliner', 'Vanilla sugar', 5);

-- Nut Cookie --

INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Nut Cookie', 'Bread crumbs', 125);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Nut Cookie', 'Chocolate', 50);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Nut Cookie', 'Egg whites', 350);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Nut Cookie', 'Fine-ground nuts', 750);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Nut Cookie', 'Ground, roasted nuts', 625);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Nut Cookie', 'Sugar', 375);

-- Nut Ring --
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Nut Ring', 'Butter', 450);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Nut Ring', 'Flour', 450);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Nut Ring', 'Roasted, chopped nuts', 225);

-- Tango --
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Tango', 'Butter', 200);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Tango', 'Flour', 300);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Tango', 'Sodium bicarbonate', 4);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Tango', 'Sugar', 250);
INSERT INTO Recipes(productName, ingredient, amount)
VALUES('Tango', 'Vanilla', 2);