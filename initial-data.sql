-- Fills the tables with default data --

-- Inserts data into the Recipes table --

-- Almond Delight --
insert into Recipes(productName, ingredient, amount)
values('Almond Delight', 'Butter', 400);
insert into Recipes(productName, ingredient, amount)
values('Almond Delight', 'Chopped almonds', 279);
insert into Recipes(productName, ingredient, amount)
values('Almond Delight', 'Cinnamon', 10);
insert into Recipes(productName, ingredient, amount)
values('Almond Delight', 'Flour', 400);
insert into Recipes(productName, ingredient, amount)
values('Almond Delight', 'Sugar', 270);

-- Amneris --
insert into Recipes(productName, ingredient, amount)
values('Amneris', 'Butter', 250);
insert into Recipes(productName, ingredient, amount)
values('Amneris', 'Eggs', 250);
insert into Recipes(productName, ingredient, amount)
values('Amneris', 'Marzipan', 750);
insert into Recipes(productName, ingredient, amount)
values('Amneris', 'Potato starch', 25); 
insert into Recipes(productName, ingredient, amount)
values('Amneris', 'Wheat flour', 25);

-- Berliner --
insert into Recipes(productName, ingredient, amount)
values('Berliner', 'Butter', 250);
insert into Recipes(productName, ingredient, amount)
values('Berliner', 'Chocolate', 50);
insert into Recipes(productName, ingredient, amount)
values('Berliner', 'Eggs', 50);
insert into Recipes(productName, ingredient, amount)
values('Berliner', 'Flour', 350);
insert into Recipes(productName, ingredient, amount)
values('Berliner', 'Icing sugar', 100);
insert into Recipes(productName, ingredient, amount)
values('Berliner', 'Vanilla sugar', 5);

-- Nut Cookie --

insert into Recipes(productName, ingredient, amount)
values('Nut Cookie', 'Bread crumbs', 125);
insert into Recipes(productName, ingredient, amount)
values('Nut Cookie', 'Chocolate', 50);
insert into Recipes(productName, ingredient, amount)
values('Nut Cookie', 'Egg whites', 350);
insert into Recipes(productName, ingredient, amount)
values('Nut Cookie', 'Fine-ground nuts', 750);
insert into Recipes(productName, ingredient, amount)
values('Nut Cookie', 'Ground, roasted nuts', 625);
insert into Recipes(productName, ingredient, amount)
values('Nut Cookie', 'Sugar', 375);

-- Nut Ring --
insert into Recipes(productName, ingredient, amount)
values('Nut Ring', 'Butter', 450);
insert into Recipes(productName, ingredient, amount)
values('Nut Ring', 'Flour', 450);
insert into Recipes(productName, ingredient, amount)
values('Nut Ring', 'Roasted, chopped nuts', 225);

-- Tango --
insert into Recipes(productName, ingredient, amount)
values('Tango', 'Butter', 200);
insert into Recipes(productName, ingredient, amount)
values('Tango', 'Flour', 300);
insert into Recipes(productName, ingredient, amount)
values('Tango', 'Sodium bicarbonate', 4);
insert into Recipes(productName, ingredient, amount)
values('Tango', 'Sugar', 250);
insert into Recipes(productName, ingredient, amount)
values('Tango', 'Vanilla', 2);

-- Inserts data into the Customers table --
INSERT INTO Customers(name, address) VALUES
('Finkakor AB', 'Helsingborg'),
('Småbröd AB', 'Malmö'),
('Kaffebröd AB', 'Landskrona'),
('Bjudkakor AB', 'Ystad'),
('Kalaskakor AB', 'Trelleborg'),
('Partykakor AB', 'Kristianstad'),
('Gästkakor AB', 'Hässleholm'),
('Skånekakor AB', 'Perstorp');

-- Inserts data into the Products table --
INSERT INTO Products(name) VALUES
('Almond delight'),
('Amneris'),
('Berliner'),
('Nut cookie'),
('Nut ring'),
('Tango');

-- Inserts data into the Ingredients table --
INSERT INTO Ingredients(name, quantity, unit) VALUES
('Bread crumbs', 100000, 'g'),
('Butter', 100000, 'g'),
('Chocolate', 100000, 'g'),
('Chopped almonds', 100000, 'g'),
('Cinnamon', 100000, 'g'),
('Egg whites', 100000, 'ml'),
('Eggs', 100000, 'g'),
('Fine-ground nuts', 100000, 'g'),
('Flour', 100000, 'g'),
('Ground, roasted nuts', 100000, 'g'),
('Icing sugar', 100000, 'g'),
('Marzipan', 100000, 'g'),
('Potato starch', 100000, 'g'),
('Roasted, chopped nuts', 100000, 'g'),
('Sodium bicarbonate', 100000, 'g'),
('Sugar', 100000, 'g'),
('Vanilla sugar', 100000, 'g'),
('Vanilla', 100000, 'g'),
('Wheat flour', 100000, 'g');








