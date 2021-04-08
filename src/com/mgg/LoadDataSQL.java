package com.mgg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//A series of methods that loads data into lists.
//Data is loaded from a SQL database using JDBC

public class LoadDataSQL {

	public static Address getAddressById(int addressId) {
		
		Address a = null;
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		String query = "select street, city, state, zip, country from Address where addressId = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, addressId);
			rs = ps.executeQuery();
			if(rs.next()) {
				String street = rs.getString("street");
				String city = rs.getString("city");
				String state = rs.getString("state");
				String zip = rs.getString("zip");
				String country = rs.getString("country");
				
				a = new Address(street, city, state, zip, country);
			} else {
				a = null;
				System.err.println("Cannot find address with ID: " + addressId);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return a;
	}
	
	public static Set<String> getEmailsByPersonId(int personId) {
		
		Set<String> emails = new HashSet<String>();
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		String query = "select e.emailAddress from Email e join Person p on e.personId = p.personId where p.personId = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				String e = rs.getString("e.emailAddress");
				emails.add(e);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return emails;
		
	}
	
	public static Person getPersonById(int personId) {
		
		Person p = null;
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		String query = "select p.personCode, p.lastName, p.firstName, p.membershipCode, a.addressId from Person p "
				+ "join Address a on p.addressId = a.addressId where personId = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, personId);
			rs = ps.executeQuery();
			if(rs.next()) {
				String personCode = rs.getString("p.personCode");
				char membershipCode = rs.getString("p.membershipCode").charAt(0);
				String lastName = rs.getString("p.lastName");
				String firstName = rs.getString("p.firstName");
				int addressId = rs.getInt("a.addressId");
				Address address = getAddressById(addressId);
				Set<String> emails = new HashSet<String>();
				emails = getEmailsByPersonId(personId);
				
				if (Character.compare(membershipCode, 'C') == 0) {
					p = new Customer(personCode, membershipCode, lastName, firstName, address, emails, "Customer", 0.0);
				} else if (Character.compare(membershipCode, 'G') == 0) {
					p = new Customer(personCode, membershipCode, lastName, firstName, address, emails, "GoldCustomer,", 0.05);
				} else if (Character.compare(membershipCode, 'P') == 0) {
					p = new Customer(personCode, membershipCode, lastName, firstName, address, emails, "PlatinumCustomer", .1);
				} else {
					p = new Employee(personCode, membershipCode, lastName, firstName, address, emails);
				}
			} else {
				p = null;
				System.err.println("Cannot find person with ID: " + personId);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return p;
	}
	
	public static Store getStoreById(int storeId) {
		
		Store s = null;
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		String query = "select s.storeCode, a.addressId, s.salespersonId from Store s "
				+ "join Address a on s.addressId = a.addressId join Person p on s.salespersonId = p.personId "
				+ "where s.storeId = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, storeId);
			rs = ps.executeQuery();
			if (rs.next()) {
				String storeCode = rs.getString("s.storeCode");
				int addressId = rs.getInt("a.addressId");
				Address address = getAddressById(addressId);
				int salespersonId = rs.getInt("s.salespersonId");
				Person manager = getPersonById(salespersonId);
				
				s = new Store(storeCode, manager, address);
			} else {
				s = null;
				System.err.println("Cannot find store with ID: " + storeId);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return s;
	}
	
	public static Item getItemById(int itemId) {
		
		Item i = null;
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		String query = "select itemCode, itemType, itemName, basePrice, hourlyRate, annualFee from Item where itemId = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, itemId);
			rs = ps.executeQuery();
			if(rs.next()) {
				String itemCode = rs.getString("itemCode");
				String itemType = rs.getString("itemType");
				String itemName = rs.getString("itemName");
				Double basePrice = rs.getDouble("basePrice");
				Double hourlyRate = rs.getDouble("hourlyRate");
				Double annualFee = rs.getDouble("annualFee");
				
				if (itemType.equals("PN")) {
					i = new NewProduct(itemCode, itemType, itemName, basePrice);
				} else if (itemType.equals("PU")) {
					i = new UsedProduct(itemCode, itemType, itemName, basePrice);
				} else if (itemType.equals("PG")) {
					i = new GiftCard(itemCode, itemType, itemName);
				} else if (itemType.equals("SV")) {
					i = new Service(itemCode, itemType, itemName, hourlyRate);
				} else {
					i = new Subscription(itemCode, itemType, itemName, annualFee);
				}
				
			} else {
				i = null;
				System.err.println("Cannot find item with ID: " + itemId);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return i;
	}
	
	public static List<Item> getSaleItemsBySaleId(int saleId) {
		
		List<Item> items = new ArrayList<Item>();
		Item i = null;
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		String query = "select si.quantity, si.giftCardPrice, si.serviceHours, si.employeeId, si.subscriptionBeginDate, "
				+ "si.subscriptionEndDate, i.itemId from SaleItem si join Person p on si.employeeId = p.personId "
				+ "join Sale s on si.saleId = s.saleId join Item i on si.itemId = i.itemId where s.saleId = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, saleId);
			rs = ps.executeQuery();
			while(rs.next()) {
				
				int quantity = rs.getInt("si.quantity");
				Double giftCardPrice = rs.getDouble("si.giftCardPrice");
				Double serviceHours = rs.getDouble("si.serviceHours");
				int employeeId = rs.getInt("si.employeeId");
				Date a = rs.getDate("si.subscriptionBeginDate"); //convert from Date to LocalDate
				LocalDate beginDate = a.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				Date b = rs.getDate("si.subscriptionEndDate");
				LocalDate endDate = b.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				int itemId = rs.getInt("i.itemId");
				
				i = getItemById(itemId);
				
				if (i.getType().equals("PN")) {
					i = new NewProductSale(i.getCode(), i.getType(), i.getName(), ((NewProductSale) i).getBasePrice(), quantity);
				} else if (i.getType().equals("PU")) {
					i = new NewProductSale(i.getCode(), i.getType(), i.getName(), ((UsedProductSale) i).getBasePrice(), quantity);
				} else if (i.getType().equals("PG")) {
					i = new GiftCardSale(i.getCode(), i.getType(), i.getName(), giftCardPrice);
				} else if (i.getType().equals("SV")) {
					Person employee = getPersonById(employeeId);
					i = new ServiceSale(i.getCode(), i.getType(), i.getName(), ((ServiceSale) i).getHourlyRate(), employee, 
							serviceHours);
				} else {
					i = new SubscriptionSale(i.getCode(), i.getType(), i.getName(), ((SubscriptionSale) i).getAnnualFee(),
							beginDate, endDate);
				}
				
				items.add(i);
				
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		return items;
	}
	
	public static List<Sale> getAllSales() {
		
		List<Sale> sales = new ArrayList<Sale>();
		List<Item> items = new ArrayList<>(); //each sale has a list of its items
		Sale sale;
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		String query = "select s.saleCode, st.storeCode, s.customerId, s.salespersonId, s.saleId from Sale s join Store st on s.storeId = st.storeId "
				+ "join Person p on s.customerId = p.personId join Person p1 on s.salespersonId = p1.personId;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				String salesCode = rs.getString("s.saleCode");
				String storeCode = rs.getString("st.storeCode");
				int customerId = rs.getInt("s.customerId");
				Person customer = getPersonById(customerId);
				int salespersonId = rs.getInt("s.salespersonId");
				Person salesperson = getPersonById(salespersonId);
				int saleId = rs.getInt("s.saleId");
				items = getSaleItemsBySaleId(saleId);
				
				sale = new Sale(salesCode, storeCode, customer, salesperson, items);
				sales.add(sale);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
				
		return sales;
	}
	
}
