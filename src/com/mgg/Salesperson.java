package com.mgg;

import java.util.ArrayList;
import java.util.List;

//Models a salesperson

import java.util.Set;

public class Salesperson extends Employee {

	public Salesperson(String personCode, char membershipCode, String lastName, String firstName, Address address,
			Set<String> emails, int numberOfSales, Double grandTotal) {
		super(personCode, membershipCode, lastName, firstName, address, emails);
		this.numberOfSales = numberOfSales;
		this.grandTotal = grandTotal;
	}
	
	private int numberOfSales;
	private Double grandTotal;
	
	public int getNumberOfSales() {
		return numberOfSales;
	}
	
	public Double getGrandTotal() {
		return grandTotal;
	}
		
	//Prints out a detailed report of salespeople at MGG
	public static void salespersonReport() {
		
		List<Person> people = new ArrayList<Person>();//List of all people
		people = LoadData.parsePersonFile();
				
		List<Person> employees = new ArrayList<Person>();//List of all employees
		
		List<Sale> sales = new ArrayList<Sale>();
		sales = LoadData.parseSaleFile();
		
		int saleCount = 0; //Total number of sales for an individual salesperson
		int fullSaleCount = 0; //Total number of sales among all salespeople
		Double grandTotal = 0.0; //Grand total for an individual salesperson
		Double fullGrandTotal = 0.0; //Grand total among all salespeople
		
		//Add employees to employee list
		for (Person p : people) { 
			if (Character.compare(p.getMembershipCode(), 'E') == 0) {
				for (Sale s : sales) {
					if (p.getPersonCode().equals(s.getSalesperson().getPersonCode())) {
						saleCount++; fullSaleCount++;
						grandTotal += s.getTotalCost();
						grandTotal = Math.round(grandTotal * 100.0) / 100.0;
					}
				}
				employees.add(new Salesperson(p.getPersonCode(), p.getMembershipCode(),
						p.getLastName(), p.getFirstName(), p.getAddress(), p.getEmails(), saleCount, grandTotal));
				
				fullGrandTotal += grandTotal;
				fullGrandTotal = Math.round(fullGrandTotal * 100.0) / 100.0;
				saleCount = 0; grandTotal = 0.0; 
			}
		}
		
		
		System.out.println("+-----------------------------------------------------+");
		System.out.println("| Salesperson Summary Report                          |");
		System.out.println("+-----------------------------------------------------+");
		System.out.println("Salesperson                    # Sales    Grand Total");
		
		
		String name;
		for (Person p : employees) {
			Double personGrandTotal = ((Salesperson) p).getGrandTotal();
			
			name = (p.getLastName()+", "+p.getFirstName()); 
			System.out.printf("%-31s%-11d$%10.2f\n", name, ((Salesperson) p).getNumberOfSales(), personGrandTotal);
			
			
		}
		
		System.out.println("+-----------------------------------------------------+");
		System.out.printf("%32d          $%10.2f\n", fullSaleCount, fullGrandTotal);
		
	}
	
}
