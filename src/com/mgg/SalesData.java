package com.mgg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Database interface class
 */
public class SalesData {

	/**
	 * Removes all sales records from the database.
	 */
	public static void removeAllSales() {
		List<Sale> sales = new ArrayList<>();
		sales = LoadDataSQL.getAllSales();

		for (Sale s : sales) {
			removeSale(s.getSalesCode());
		}
	}

	/**
	 * Removes the single sales record associated with the given
	 * <code>saleCode</code>
	 * 
	 * @param saleCode
	 */
	public static void removeSale(String saleCode) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String saleItemQuery = "delete from SaleItem where saleId = ?;";
		String saleQuery = "delete from Sale where saleId = ?;";

		PreparedStatement ps = null;
		int saleId = getSaleId(saleCode);
		try {

			ps = conn.prepareStatement(saleItemQuery);
			ps.setInt(1, saleId);
			ps.executeUpdate();

			ps = conn.prepareStatement(saleQuery);
			ps.setInt(1, saleId);
			ps.executeUpdate();

			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Clears all tables of the database of all records.
	 */
	public static void clearDatabase() {
		removeAllSales();

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String[] tables = { "Store;", "Email;", "Person;", "Address;", "Item;" };
		StringBuilder query = new StringBuilder();
		query.append("delete from ");

		PreparedStatement ps = null;
		try {

			for (String s : tables) {
				query.append(s);

				ps = conn.prepareStatement(query.toString());
				ps.executeUpdate();

				query.delete(12, query.length());
			}

			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method to add a person record to the database with the provided data. The
	 * <code>type</code> will be one of "E", "G", "P" or "C" depending on the type
	 * (employee or type of customer).
	 * 
	 * @param personCode
	 * @param type
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String type, String firstName, String lastName, String street,
			String city, String state, String zip, String country) {
		
		addAddress(street, city, state, zip, country);

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String addressIdQuery = "select addressId from Address where street = ? and zip = ?;";
		String personQuery = "insert into Person (personCode, lastName, firstName, membershipCode, addressId) values "
				+ "(?, ?, ?, ?, ?);";

		PreparedStatement ps = null;
		ResultSet rs = null;
		int addressId = -1;
		try {
			ps = conn.prepareStatement(addressIdQuery);
			ps.setString(1, street);
			ps.setString(2, zip);
			rs = ps.executeQuery();
			if (rs.next()) {
				addressId = rs.getInt("addressId");
			}

			ps = conn.prepareStatement(personQuery);
			ps.setString(1, personCode);
			ps.setString(2, lastName);
			ps.setString(3, firstName);
			ps.setString(4, type);
			ps.setInt(5, addressId);
			ps.executeUpdate();

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String emailQuery = "insert into Email (emailAddress, personId) values (?, ?);";
		PreparedStatement ps = null;
		int personId = getPersonId(personCode);
		try {
			ps = conn.prepareStatement(emailQuery);
			ps.setString(1, email);
			ps.setInt(2, personId);
			ps.executeUpdate();

			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Adds a store record to the database managed by the person identified by the
	 * given code.
	 * 
	 * @param storeCode
	 * @param managerCode
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addStore(String storeCode, String managerCode, String street, String city, String state,
			String zip, String country) {

		addAddress(street, city, state, zip, country);

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String addressIdQuery = "select addressId from Address where street = ? and zip = ?;";
		String storeQuery = "insert into Store (storeCode, addressId, salespersonId) values (?, ?, ?);";

		PreparedStatement ps = null;
		ResultSet rs = null;
		int personId = getPersonId(managerCode);
		int addressId = -1;
		try {
			ps = conn.prepareStatement(addressIdQuery);
			ps.setString(1, street);
			ps.setString(2, zip);
			rs = ps.executeQuery();
			if (rs.next()) {
				addressId = rs.getInt("addressId");
			}

			ps = conn.prepareStatement(storeQuery);
			ps.setString(1, storeCode);
			ps.setInt(2, addressId);
			ps.setInt(3, personId);
			ps.executeUpdate();

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds an address using the given information.
	 * 
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addAddress(String street, String city, String state, String zip, String country) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String query = "insert into Address (street, city, state, zip, country) values " + "(?, ?, ?, ?, ?);";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);
			ps.executeUpdate();

			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a sales item (product, service, subscription) record to the database
	 * with the given <code>name</code> and <code>basePrice</code>. The type of item
	 * is specified by the <code>type</code> which may be one of "PN", "PU", "PG",
	 * "SV", or "SB". These correspond to new products, used products, gift cards
	 * (for which <code>basePrice</code> will be <code>null</code>), services, and
	 * subscriptions.
	 * 
	 * @param itemCode
	 * @param type
	 * @param name
	 * @param basePrice
	 */
	public static void addItem(String itemCode, String type, String name, Double basePrice) {
		Double hourlyRate = null;
		Double annualFee = null;
		if (type.equals("SV")) {
			hourlyRate = basePrice;
		} else if (type.equals("SB")) {
			annualFee = basePrice;
		}

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String query = "insert into Item (itemCode, itemType, itemName, basePrice, hourlyRate, annualFee) values "
				+ "(?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, itemCode);
			ps.setString(2, type);
			ps.setString(3, name);
			if (basePrice != null) {
				ps.setDouble(4, basePrice);
			} else {
				ps.setNull(4, java.sql.Types.DOUBLE);
			}
			if (hourlyRate != null) {
				ps.setDouble(5, hourlyRate);
			} else {
				ps.setNull(5, java.sql.Types.DOUBLE);
			}
			if (annualFee != null) {
				ps.setDouble(6, annualFee);
			} else {
				ps.setNull(6, java.sql.Types.DOUBLE);
			}

			ps.executeUpdate();

			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a sales record to the database with the given data.
	 * 
	 * @param saleCode
	 * @param storeCode
	 * @param customerCode
	 * @param salesPersonCode
	 */
	public static void addSale(String saleCode, String storeCode, String customerCode, String salesPersonCode) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String storeIdQuery = "select storeId from Store where storeCode = ?;";
		String personIdQuery = "select personId from Person where personCode = ?;";
		String saleQuery = "insert into Sale (saleCode, storeId, customerId, salespersonId) values " + "(?, ?, ?, ?);";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int storeId = -1;
		int customerId = -1;
		int salespersonId = -1;
		try {
			ps = conn.prepareStatement(storeIdQuery);
			ps.setString(1, storeCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				storeId = rs.getInt("storeId");
			}

			ps = conn.prepareStatement(personIdQuery);
			ps.setString(1, customerCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				customerId = rs.getInt("personId");
			}

			ps.setString(1, salesPersonCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				salespersonId = rs.getInt("personId");
			}

			ps = conn.prepareStatement(saleQuery);
			ps.setString(1, saleCode);
			ps.setInt(2, storeId);
			ps.setInt(3, customerId);
			ps.setInt(4, salespersonId);
			ps.executeUpdate();

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a particular product (new or used, identified by <code>itemCode</code>)
	 * to a particular sale record (identified by <code>saleCode</code>) with the
	 * specified quantity.
	 * 
	 * @param saleCode
	 * @param itemCode
	 * @param quantity
	 */
	public static void addProductToSale(String saleCode, String itemCode, int quantity) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String query = "insert into SaleItem (quantity, giftCardPrice, serviceHours, employeeId, subscriptionBeginDate, "
				+ "subscriptionEndDate, saleId, itemId) values (?, null, null, null, null, null, ? ,?);";
		PreparedStatement ps = null;
		int saleId = getSaleId(saleCode);
		int itemId = getItemId(itemCode);
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, quantity);
			ps.setInt(2, saleId);
			ps.setInt(3, itemId);
			ps.executeUpdate();

			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Adds a particular gift card (identified by <code>itemCode</code>) to a
	 * particular sale record (identified by <code>saleCode</code>) in the specified
	 * amount.
	 * 
	 * @param saleCode
	 * @param itemCode
	 * @param amount
	 */
	public static void addGiftCardToSale(String saleCode, String itemCode, double amount) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String query = "insert into SaleItem (quantity, giftCardPrice, serviceHours, employeeId, subscriptionBeginDate, "
				+ "subscriptionEndDate, saleId, itemId) values (null, ?, null, null, null, null, ? ,?);";
		PreparedStatement ps = null;
		int saleId = getSaleId(saleCode);
		int itemId = getItemId(itemCode);
		try {
			ps = conn.prepareStatement(query);
			ps.setDouble(1, amount);
			ps.setInt(2, saleId);
			ps.setInt(3, itemId);
			ps.executeUpdate();

			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a particular service (identified by <code>itemCode</code>) to a
	 * particular sale record (identified by <code>saleCode</code>) which will be
	 * performed by the given employee for the specified number of hours.
	 * 
	 * @param saleCode
	 * @param itemCode
	 * @param employeeCode
	 * @param billedHours
	 */
	public static void addServiceToSale(String saleCode, String itemCode, String employeeCode, double billedHours) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String query = "insert into SaleItem (quantity, giftCardPrice, serviceHours, employeeId, subscriptionBeginDate, "
				+ "subscriptionEndDate, saleId, itemId) values (null, null, ?, ?, null, null, ? ,?);";
		PreparedStatement ps = null;
		int personId = getPersonId(employeeCode);
		int saleId = getSaleId(saleCode);
		int itemId = getItemId(itemCode);
		try {
			ps = conn.prepareStatement(query);
			ps.setDouble(1, billedHours);
			ps.setInt(2, personId);
			ps.setInt(3, saleId);
			ps.setInt(4, itemId);
			ps.executeUpdate();

			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds a particular subscription (identified by <code>itemCode</code>) to a
	 * particular sale record (identified by <code>saleCode</code>) which is
	 * effective from the <code>startDate</code> to the <code>endDate</code>
	 * inclusive of both dates.
	 * 
	 * @param saleCode
	 * @param itemCode
	 * @param startDate
	 * @param endDate
	 */
	public static void addSubscriptionToSale(String saleCode, String itemCode, String startDate, String endDate) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String query = "insert into SaleItem (quantity, giftCardPrice, serviceHours, employeeId, subscriptionBeginDate, "
				+ "subscriptionEndDate, saleId, itemId) values (null, null, null, null, ?, ?, ? ,?);";
		PreparedStatement ps = null;
		int saleId = getSaleId(saleCode);
		int itemId = getItemId(itemCode);
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, startDate);
			ps.setString(2, endDate);
			ps.setInt(3, saleId);
			ps.setInt(4, itemId);
			ps.executeUpdate();

			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Returns a Person's primary key given their alphanumeric Person Code
	 * @param personCode
	 * @return
	 */
	public static int getPersonId(String personCode) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String query = "select personId from Person where personCode = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int personId = -1;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				personId = rs.getInt("personId");
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return personId;
	}

	/**
	 * Returns a Sale's primary key given its alphanumeric Sale Code
	 * @param saleCode
	 * @return
	 */
	public static int getSaleId(String saleCode) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String query = "select saleId from Sale where saleCode = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int saleId = -1;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, saleCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				saleId = rs.getInt("saleId");
			}

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return saleId;
	}

	/**
	 * Returns an Item's primary key given its alphanumeric Item Code
	 * @param itemCode
	 * @return
	 */
	public static int getItemId(String itemCode) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String query = "select itemId from Item where itemCode = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int itemId = -1;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, itemCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				itemId = rs.getInt("itemId");
			}

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return itemId;
	}

}
