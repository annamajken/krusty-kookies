package krusty;

import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Map;
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
		return "{\"pallets\":[]}";
	}

	public String reset(Request req, Response res) {
		return "{}";
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
			updateIngredients(product);
			return "{ \r\n  \"status\": \"ok\", \r\n  \"id\": " + rs.getInt("productID") + " \r\n}}";
		} catch (SQLException exception) {
			System.err.println(exception);
			exception.printStackTrace();
			return "{ \r\n  \"status\": \"error\" \r\n}";
		}
	}
	
	private void updateIngredients(String product) {
		
	}
}
