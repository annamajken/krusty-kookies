package krusty;

import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import static krusty.Jsonizer.toJson;

public class Database {
	/**
	 * Modify it to fit your environment and then use this string when connecting to your database!
	 */
	private static final String jdbcString = "jdbc:mysql://puccini.cs.lth.se/db08";
	
	// For use with MySQL or PostgreSQL
	private static final String jdbcUsername = "db08";
	private static final String jdbcPassword = "qcd274gi";

	private Connection connection;
	
	public void connect() {
		try {
			connection = DriverManager.getConnection(jdbcString, jdbcUsername, jdbcPassword);
		} catch (SQLException exception) {
			System.err.println(exception);
			exception.printStackTrace();
		}
	}

	// TODO: Implement and change output in all methods below!

	public String getCustomers(Request req, Response res) {
		String sql = "SELECT name, address FROM Customers";
		
		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			String json = Jsonizer.toJson(rs, "customers");
			return json;
			
		} catch (SQLException exception) {
			System.err.println(exception);
			exception.printStackTrace();
			return Jsonizer.anythingToJson("error", "status");
			
		}
	}
	
	public String getRawMaterials(Request req, Response res) {
		String sql = "SELECT name, quantity AS amount, unit FROM Ingredients";

		try(PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			String json = Jsonizer.toJson(rs, "raw-materials");
			return json;

		} catch (SQLException exception) {
			System.err.println(exception);
			exception.printStackTrace();
			return Jsonizer.anythingToJson("error", "status");			
		}
	}

	public String getCookies(Request req, Response res) {
		return "{\"cookies\":[]}";
	}

	public String getRecipes(Request req, Response res) {
		return "{}";
	}

	public String getPallets(Request req, Response res) {
		String cookie = req.queryParams("cookie");
		String dateFrom = req.queryParams("from");
		String dateTo = req.queryParams("to");
		String blocked = req.queryParams("blocked");
		String sql = "SELECT palletNbr AS id, productName AS cookie,"
				+ "dateAndTimeOfProduction AS production_date, name, IF(blocked, 'yes', 'no') AS blocked"
				+ " FROM Pallets LEFT JOIN Products ON Products.productID = Pallets.productID"
				+ " LEFT JOIN Orders ON Orders.orderNbr = Pallets.orderNbr"
				+ " LEFT JOIN Customers ON Orders.customer = Customers.customerID"
				+ " WHERE 1=1";
		
		if (cookie != null) sql += " AND productName = ?";
		if (dateFrom != null) sql += "AND dateAndTimeOfProduction > ?";
		if (dateTo != null) sql += " AND dateAndTimeOfProduction < ?";
		if (blocked != null) sql += " AND blocked = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			int index = 1;
			if (cookie != null) {ps.setString(index, cookie); index++;}
			if (dateFrom != null) {ps.setTimestamp(index, Timestamp.valueOf(dateFrom + " 00:00:00")); index++;}
			if (dateTo != null) {ps.setTimestamp(index, Timestamp.valueOf(dateTo + " 23:59:59")); index++;}
			if (blocked != null) {ps.setBoolean(index, blocked == "yes");}
			ResultSet rs = ps.executeQuery();
			return Jsonizer.toJson(rs, "pallets");
		} catch (SQLException exception) {
			System.err.println(exception);
			exception.printStackTrace();
			return Jsonizer.anythingToJson("error", "status");
		}
	}

	public String reset(Request req, Response res) {
		// SET FOREIGN KEYS = 0
		try (PreparedStatement ps = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0")) {
			ps.executeUpdate();
		} catch (SQLException exception) {
			System.err.println(exception);
			exception.printStackTrace();
		}
		String[] toTruncate = {"Ingredients", "Products", "Recipes", "Customers", "Orders", "Pallets", "OrderSpecs"};
		for(String string : toTruncate) {
			try (PreparedStatement ps = connection.prepareStatement("TRUNCATE TABLE " + string)) {
				ps.executeUpdate();
			} catch (SQLException exception) {
				System.err.println(exception);
				exception.printStackTrace();
			}
		}
		try (PreparedStatement ps = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 1")) {
			ps.executeUpdate();
		} catch (SQLException exception) {
			System.err.println(exception);
			exception.printStackTrace();
		}
		// Insert customers
		insertCustomer("Bjudkakor AB", "Ystad");
		insertCustomer("Finkakor AB", "Helsingborg");
		insertCustomer("Gästkakor AB", "Hässleholm");
		insertCustomer("Kaffebröd AB", "Landskrona");
		insertCustomer("Kalaskakor AB", "Trelleborg");
		insertCustomer("Partykakor AB", "Kristianstad");
		insertCustomer("Skånekakor AB", "Perstorp");
		insertCustomer("Småbröd AB", "Malmö");
		// Insert cookies
		insertProduct("Almond delight");
		insertProduct("Amneris");
		insertProduct("Berliner");
		insertProduct("Nut cookie");
		insertProduct("Nut ring");
		insertProduct("Tango");
		// Insert ingredients
		insertIngredient("Bread crumbs", 500000, "g");
		insertIngredient("Butter", 500000, "g");
		insertIngredient("Chocolate", 500000, "g");
		insertIngredient("Chopped almonds", 500000, "g");
		insertIngredient("Cinnamon", 500000, "g");
		insertIngredient("Egg whites", 500000, "ml");
		insertIngredient("Eggs", 500000, "g");
		insertIngredient("Fine-ground nuts", 500000, "g");
		insertIngredient("Flour", 500000, "g");
		insertIngredient("Ground, roasted nuts", 500000, "g");
		insertIngredient("Icing sugar", 500000, "g");
		insertIngredient("Marzipan", 500000, "g");
		insertIngredient("Potato starch", 500000, "g");
		insertIngredient("Roasted, chopped nuts", 500000, "g");
		insertIngredient("Sodium bicarbonate", 500000, "g");
		insertIngredient("Sugar", 500000, "g");
		insertIngredient("Vanilla sugar", 500000, "g");
		insertIngredient("Vanilla", 500000, "g");
		insertIngredient("Wheat flour", 500000, "g");
		// Insert recipes
		insertRecipe("Almond delight", "Butter", 400);
		insertRecipe("Almond delight", "Chopped almonds", 279);
		insertRecipe("Almond delight", "Cinnamon", 10);
		insertRecipe("Almond delight", "Flour", 400);
		insertRecipe("Almond delight", "Sugar", 270);
		insertRecipe("Amneris", "Butter", 250);
		insertRecipe("Amneris", "Eggs", 250);
		insertRecipe("Amneris", "Marzipan", 750);
		insertRecipe("Amneris", "Potato starch", 25);
		insertRecipe("Amneris", "Wheat flour", 25);
		insertRecipe("Berliner", "Butter", 250);
		insertRecipe("Berliner", "Chocolate", 50);
		insertRecipe("Berliner", "Eggs", 50);
		insertRecipe("Berliner", "Flour", 350);
		insertRecipe("Berliner", "Icing sugar", 100);
		insertRecipe("Berliner", "Vanilla sugar", 5);
		insertRecipe("Nut cookie", "Bread crumbs", 125);
		insertRecipe("Nut cookie", "Chocolate", 50);
		insertRecipe("Nut cookie", "Egg whites", 350);
		insertRecipe("Nut cookie", "Fine-ground nuts", 750);
		insertRecipe("Nut cookie", "Ground, roasted nuts", 625);
		insertRecipe("Nut cookie", "Sugar", 375);
		insertRecipe("Nut ring", "Butter", 450);
		insertRecipe("Nut ring", "Flour", 450);
		insertRecipe("Nut ring", "Icing sugar", 190);
		insertRecipe("Nut ring", "Roasted, chopped nuts", 225);
		insertRecipe("Tango", "Butter", 200);
		insertRecipe("Tango", "Flour", 300);
		insertRecipe("Tango", "Sodium bicarbonate", 4);
		insertRecipe("Tango", "Sugar", 250);
		insertRecipe("Tango", "Vanilla", 2);
		return Jsonizer.anythingToJson("ok", "status");
	}
	
	// Helper method
	private void insertCustomer(String name, String address) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO Customers(name, address) VALUES (?, ?)")) {
			ps.setString(1, name);
			ps.setString(2, address);
			ps.executeUpdate();
		} catch (SQLException exception) {
			System.err.println(exception);
			exception.printStackTrace();
		}
	}
	
	// Helper method
	private void insertProduct(String productName) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO Products(productName) VALUES (?)")) {
			ps.setString(1, productName);
			ps.executeUpdate();
		} catch (SQLException exception) {
			System.err.println(exception);
			exception.printStackTrace();
		}
	}
	
	// Helper method
	private void insertIngredient(String name, int quantity, String unit) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO Ingredients(name, quantity, unit) VALUES (?, ?, ?)")) {
			ps.setString(1, name);
			ps.setInt(2, quantity);
			ps.setString(3, unit);
			ps.executeUpdate();
		} catch (SQLException exception) {
			System.err.println(exception);
			exception.printStackTrace();
		}
	}
	
	// Helper method
	private void insertRecipe(String productName, String rawMaterial, int amount) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO Recipes(productID, rawMaterial, amount)"
				+ " SELECT productID, ?, ? FROM Products WHERE Products.productName = ?")) {
			ps.setString(1, rawMaterial);
			ps.setInt(2, amount);
			ps.setString(3, productName);
			ps.executeUpdate();
		} catch (SQLException exception) {
			System.err.println(exception);
			exception.printStackTrace();
		}
	}

	public String createPallet(Request req, Response res) {		
		String product = req.queryParams("cookie");
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO Pallets (dateAndTimeOfProduction, productID) "
				+ "SELECT NOW(), productID FROM Products WHERE productName = ?",
				Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, product);
			ps.executeUpdate();
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				updateWarehouse(product);
				return "{\"status\": \"ok\", \"id\": " + generatedKeys.getInt(1) + "}}";
			} else {
				return Jsonizer.anythingToJson("unknown cookie", "status");
			}
		} catch (SQLException exception) {
			System.err.println(exception);
			exception.printStackTrace();
			return Jsonizer.anythingToJson("error", "status");
		}
	}
	
	
	// Helper method
	private void updateWarehouse(String product) {
		Map<String, Integer> values = new HashMap<String, Integer>();
		try (PreparedStatement ps = connection.prepareStatement("SELECT rawMaterial, amount"
				+ " FROM Recipes LEFT JOIN Products on Recipes.productID = Products.productID"
				+ " WHERE Products.productName = ?")) {
			ps.setString(1, product);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				values.put(rs.getString("rawMaterial"), rs.getInt("amount"));	// Value = rawMaterial, Key = amount
			}
		} catch (SQLException exception) {
			System.err.println(exception);
			exception.printStackTrace();
		}
		
		try (PreparedStatement ps = connection.prepareStatement("UPDATE Ingredients SET quantity=quantity-54*? WHERE name = ?")) {
			for (Entry<String, Integer> entry : values.entrySet()) {
				ps.setInt(1, entry.getValue());
				ps.setString(2, entry.getKey());
				ps.executeUpdate();
			}
		} catch (SQLException exception) {
			System.err.println(exception);
			exception.printStackTrace();
		}
	}
	
}
