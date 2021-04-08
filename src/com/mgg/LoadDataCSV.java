package com.mgg;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

//A series of methods that loads data into lists.
//Data is loaded from several .CSV files.

public class LoadDataCSV {
	
		//File input from Persons.csv
		//Reads from the .csv file and adds it to a list
		public static List<Person> parsePersonFile() {
			List<Person> result = new ArrayList<Person>();
			File f = new File("data/Persons.csv");
			try(Scanner s = new Scanner(f)) {
				String line = s.nextLine(); //Skips first line
				while(s.hasNext()) {
					line = s.nextLine();
					if(!line.trim().isEmpty()) {
						Person p = null;
						String tokens[] = line.split(",");					
						String personCode = tokens[0];
						char membershipCode = tokens[1].charAt(0);
						String lastName = tokens[2];
						String firstName = tokens[3];
						Address address = new Address(tokens[4], tokens[5], tokens[6], tokens[7], tokens[8]);
						Set<String> emails = new HashSet<String>();
						for (int i = 9; i<tokens.length; i++) { //Checks for any and all possible emails
							emails.add(tokens[i]);
						}
						//Determine what kind of member each person is, and creates
						//the appropriate subclass of person
						if(tokens[1].equals("C")) {
							p = new Customer(personCode, membershipCode, lastName, firstName, address, emails, "Customer", 0.0);
						} else if(tokens[1].equals("G")) {
							p = new Customer(personCode, membershipCode, lastName, firstName, address, emails, "GoldCustomer,", 0.05);
						} else if(tokens[1].equals("P")) {
							p = new Customer(personCode, membershipCode, lastName, firstName, address, emails, "PlatinumCustomer", .1);
						} else {
							p = new Employee(personCode, membershipCode, lastName, firstName, address, emails);
						}
						
						result.add(p);
					}
					
				}
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
			
			return result;
		}
		
		//File input from Stores.csv
		public static List<Store> parseStoreFile() {
			List<Store> result = new ArrayList<Store>();
			File f = new File("data/Stores.csv");
			try(Scanner s = new Scanner(f)) {
				String line = s.nextLine(); 
				while(s.hasNext()) {
					line = s.nextLine();
					if(!line.trim().isEmpty()) {
						Store h = null;
						String tokens[] = line.split(",");
						String storeCode = tokens[0];
						
						//Running the given manager code through a list of
						//people to have the manager be listed as a Person
						Person manager = null;
						for (Person p : parsePersonFile()) {
							if (tokens[1].equals(p.getPersonCode())) {
								manager = p;
							}
						}
						
						Address address = new Address(tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
						h = new Store(storeCode, manager, address);
						result.add(h);
					}
				}
				
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
			
			return result;
		}
		
		//File input from Items.csv
		public static List<Item> parseItemFile() {
			List<Item> result = new ArrayList<Item>();
			File f = new File("data/Items.csv");
			try(Scanner s = new Scanner(f)) {
				String line = s.nextLine();
				while(s.hasNext()) {
					line = s.nextLine();
					if(!line.trim().isEmpty()) {
						Item i = null;
						String tokens[] = line.split(",");
						String code = tokens[0];
						String type = tokens[1];
						String name = tokens[2];
						Double price;
						if (tokens.length < 4) {
							price = null;
						}
						else {
							price = Double.parseDouble(tokens[3]);
						}
						
						if(tokens[1].equals("PN")) {
							i = new NewProduct(code, type, name, price);
						} else if(tokens[1].equals("PU")) {
							i = new UsedProduct(code, type, name, price);
						} else if(tokens[1].equals("PG")) {
							i = new GiftCard(code, type, name);
						} else if(tokens[1].equals("SV")) {
							i = new Service(code, type, name, price);
						} else {
							i = new Subscription(code, type, name, price);
						}
						
						result.add(i);
					}
				}
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
			
			return result;
		}
		
		//File input from Sales.csv
		public static List<Sale> parseSaleFile() {
			List<Sale> result = new ArrayList<Sale>();
			File f = new File("data/Sales.csv");
			try(Scanner s = new Scanner(f)) {
				String line = s.nextLine(); 
				while(s.hasNext()) {
					line = s.nextLine();
					if(!line.trim().isEmpty()) {
						String tokens[] = line.split(",");
						String salesCode = tokens[0];
						String storeCode = tokens[1];
						
						String customerCode = tokens[2]; 
						Person customer = new Customer(null, 'h', null, null, null, null, null, null); //placeholder initialization			
						String salespersonCode = tokens[3];
						Person salesperson = new Employee(null, 'h', null, null, null, null);
						List<Person> people = new ArrayList<Person>();
						people = parsePersonFile();
						for (Person p : people) {
							if (p.getPersonCode().equals(customerCode)) {
								customer = p;
							}
							else if (p.getPersonCode().equals(salespersonCode)) {
								salesperson = p;
							}
						}
						
						//Running the first given item code through a list of
						//items to determine what kind of item it is, then looping
						//through all the items to add to Sale.java's itemList
						List<Item> itemList = new ArrayList<Item>(); //Item list given to Sale.java
						List<Item> allItems = new ArrayList<Item>(); //List of all items in Items.csv
						allItems = parseItemFile();
						
						for (int i=4; i<tokens.length;i+=2) {
							for (Item j : allItems) {

								
								if (tokens[i].equals(j.getCode())) {
									//Find out what type of item it is and 
									//add appropriate data to result 
									if (j.getType().equals("PN")) {
										Item NewProductSale = new NewProductSale(j.getCode(), j.getType(), 
												j.getName(), ((NewProduct) j).getBasePrice(), 
												Integer.parseInt(tokens[i+1]));
										itemList.add(NewProductSale);
									} 
									else if (j.getType().equals("PU")) {
										Item UsedProductSale = new UsedProductSale(j.getCode(), j.getType(), 
												j.getName(), ((UsedProduct) j).getBasePrice(), 
												Integer.parseInt(tokens[i+1]));
										itemList.add(UsedProductSale);
									}
									else if (j.getType().equals("PG")) {
										Item giftCardSale = new GiftCardSale(j.getCode(), 
												j.getType(), j.getName(), Double.valueOf(tokens[i+1]));
										itemList.add(giftCardSale);
									}
									else if (j.getType().equals("SV")) {
										
										
										Item serviceSale = new ServiceSale(j.getCode(),
												j.getType(), j.getName(), ((Service) j).getHourlyRate(), 
												salesperson, Double.valueOf(tokens[i+2]));
										itemList.add(serviceSale);
										i++;
									}
									else {
										Item subscriptionSale = new SubscriptionSale(j.getCode(), 
												j.getType(), j.getName(), ((Subscription) j).getAnnualFee(), 
												LocalDate.parse(tokens[i+1]), LocalDate.parse(tokens[i+2]));
										itemList.add(subscriptionSale);
										i++;
									}
								}
							}							
						}
						
						//System.out.println(salesCode);
						
						result.add(new Sale(salesCode, storeCode, customer, salesperson, itemList));
						
					}
				}
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
			
			return result;
		}
	
}
