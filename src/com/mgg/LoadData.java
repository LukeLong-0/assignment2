package com.mgg;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class LoadData {

	//File input from persons.csv
		//Reads from the csv file and adds it to a list
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
						Address address = new Address(tokens[4], tokens[5], tokens[6], 
								Integer.parseInt(tokens[7]), tokens[8]);
						Set<String> emails = new HashSet<String>();
						for (int i = 9; i<tokens.length; i++) { //Checks for any and all possible emails
							emails.add(tokens[i]);
						}
						//Determine what kind of member each person is, and creates
						//the appropriate subclass of person
						if(tokens[1].equals("C")) {
							p = new Customer(personCode, membershipCode, lastName, firstName, address, emails);
						} else if(tokens[1].equals("G")) {
							p = new Gold(personCode, membershipCode, lastName, firstName, address, emails);
						} else if(tokens[1].equals("P")) {
							p = new Platinum(personCode, membershipCode, lastName, firstName, address, emails);
						} else if(tokens[1].equals("E")) {
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
		
		//File input from stores.csv
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
						
						Person manager = null;
						List<Person> personList = parsePersonFile();
						for (Person p : personList) {
							if (tokens[1] == p.getPersonCode()) {
								manager = p;
							}
						}
						
						Address address = new Address(tokens[2], tokens[3], tokens[4], 
								Integer.parseInt(tokens[5]), tokens[6]);
						h = new Store(storeCode, manager, address);
						result.add(h);
					}
				}
				
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
			
			return result;
		}
		
		//File input from items.csv
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
							i = new GiftCard(code, type, name, null);
						} else if(tokens[1].equals("SV")) {
							i = new Service(code, type, name, price);
						} else if(tokens[1].equals("SB")) {
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
	
}
