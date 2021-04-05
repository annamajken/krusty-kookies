package krusty;

import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	private static final String jdbcString = "jdbc:mysql://localhost/krusty";
	
	// For use with MySQL or PostgreSQL
	private static final String jdbcUsername = "<CHANGE ME>";
	private static final String jdbcPassword = "<CHANGE ME>";

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
		return "{}";
	}

	public String getRawMaterials(Request req, Response res) {
		return "{}";
	}

	public String getCookies(Request req, Response res) {
		return "{\"cookies\":[]}";
	}

	public String getRecipes(Request req, Response res) {
		return "{}";
	}

	public String getPallets(Request req, Response res) {
		ArrayList<String> values = new ArrayList<String>();
		String sql = "SELECT palletNbr AS id, cookieName AS cookie,"
				+ "dateAndTimeOfProduction AS production_date, name, IF(blocked, 'yes', 'no')"
				+ " FROM Pallets LEFT JOIN Products ON Products.productID = Pallets.productID"
				+ " LEFT JOIN Orders ON Orders.orderNbr = Products.orderNbr"
				+ " LEFT JOIN Customers ON Orders.customer = customer.customerID"
				+ " WHERE 1=1";
		
		if (req.queryParams("cookie") != null) {
			sql += " AND productName = ?";
			values.add(req.queryParams("cookie"));
		}
		if (req.queryParams("from") != null) {
			sql += "AND dateAndTimeOfProduction > ?";
			values.add(req.queryParams("from"));
		}
		if (req.queryParams("to") != null) {
			sql += " AND dateAndTimeOfProduction < ?";
			values.add(req.queryParams("to"));
		}

		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			for (int i = 0; i < values.size(); i++) {
				ps.setString(i+1, values.get(i)); 
			}
			ResultSet rs = ps.executeQuery();
			String json = JSONizer.toJSON(rs, "pallets");
			return json;
		} catch (SQLException exception) {
			System.err.println(exception);
			exception.printStackTrace();
			return "{ \r\n  \"status\": \"error\" \r\n}";
		}
	}

	public String reset(Request req, Response res) {
		// Clear all tables
		try (PreparedStatement ps = connection.prepareStatement("TRUNCATE TABLE Ingredients,"
				+ " Products, Recipes, Customers, Orders, Pallets, OrderSpecs")) {
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
		insertIngredient("Egg whites", 500000, "g");
		insertIngredient("Eggs", 500000, "g");
		insertIngredient("Fine-ground nuts", 500000, "g");
		insertIngredient("Flour", 500000, "g");
		insertIngredient("Ground, rousted nuts", 500000, "g");
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
		insertRecipe("Nut cookie", "Ground, rousted nuts", 625);
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
		return "{ \r\n  \"status\": \"ok\" \r\n}";
	}
	
	// Helper method
	private void insertCustomer(String name, String address) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO Customers(name, address) VALUES (?, ?)")) {
			ps.setString(1, name);
			ps.setString(2, name);
			ps.executeUpdate();
		} catch (SQLException exception) {
			System.err.println(exception);
			exception.printStackTrace();
		}
	}
	
	// Helper method
	private void insertProduct(String productName) {
		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO Cookies(productName) VALUES (?)")) {
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
		try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM Products WHERE name = ?")) {
			ps.setString(1, product);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) return "{ \r\n  \"status\": \"unknown cookie\" \r\n}";
		} catch (SQLException exception) {
			System.err.println(exception);
			exception.printStackTrace();
			return "{ \r\n  \"status\": \"error\" \r\n}";
		}
		
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO Pallets (productID) SELECT productID FROM Products WHERE name = ?",
				Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, product);
			ResultSet rs = ps.executeQuery();
			updateWarehouse(product);
			return "{ \r\n  \"status\": \"ok\", \r\n  \"id\": " + rs.getInt("productID") + " \r\n}}";
		} catch (SQLException exception) {
			System.err.println(exception);
			exception.printStackTrace();
			return "{ \r\n  \"status\": \"error\" \r\n}";
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
				values.put(rs.getString("rawMaterial"), rs.getInt("amount"));
			}
		} catch (SQLException exception) {
			System.err.println(exception);
			exception.printStackTrace();
		}
		try (PreparedStatement ps = connection.prepareStatement("ALTER TABLE Ingredients SET amount = amount - ? WHERE rawMaterial = ?")) {
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
