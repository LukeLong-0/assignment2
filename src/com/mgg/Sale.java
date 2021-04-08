package com.mgg;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

//Models a sale, along with Sale utilities.

public class Sale {
	
	private String salesCode;
	private String storeCode;
	private Person customer;
	private Person salesperson;
	private List<Item> items;
	
	public Sale(String salesCode, String storeCode, Person customer, 
			Person salesperson, List<Item> items) {
		this.salesCode = salesCode;
		this.storeCode = storeCode;
		this.customer = customer;
		this.salesperson = salesperson;
		this.items = items;
	}

	public String getSalesCode() {
		return salesCode;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public Person getCustomer() {
		return customer;
	}

	public Person getSalesperson() {
		return salesperson;
	}

	public List<Item> getItems() {
		return items;
	}
	
	
	public Double getTotalCost() {
		Double result = 0.0;
		
		//Add the cost of every item in the sale, including tax
		for (Item i : this.items) {
			result += i.getTotalCost();
			result = Math.round(result * 100.0) / 100.0;
		}
		
		
		//Find and subtract the discount
		Double discount = this.getCustomer().getDiscount();
		result = result - (Math.round((result * discount) * 100.0) / 100.0);
		return result;
	} 
	
	
	//Prints out a "Detailed" sales report
	public void saleReport() {
		
		Person customer = this.getCustomer();
		Person salesperson = this.getSalesperson();
		
		//Print customer information
		System.out.printf("Sale%7s\nStore%7s\nCustomer:\n", this.getSalesCode(), this.getStoreCode());
		System.out.printf("%s, %s ([", customer.getFirstName(), customer.getLastName());
		int count = 0; //Print emails
		for (String s : customer.getEmails()) {
			if (count < (customer.getEmails().size() - 1)) {
				System.out.printf("%s, ", s);
			} else {
				System.out.printf("%s])\n", s);
			}
			count++;
		}
		System.out.printf("    %4s\n    %4s, %s %s %s\n\n", customer.getAddress().getStreet(), customer.getAddress().getCity(), 
				customer.getAddress().getState(), customer.getAddress().getZip(), customer.getAddress().getCountry());
		
		//Print salesperson information
		System.out.printf("Sales Person:\n%s, %s ([", salesperson.getLastName(), salesperson.getFirstName());
		count = 0; //Print emails
		for (String s : salesperson.getEmails()) {
			if (count < (salesperson.getEmails().size() - 1)) {
				System.out.printf("%s, ", s);
			} else {
				System.out.printf("%s])\n", s);
			}
			count++;
		}
		System.out.printf("    %4s\n    %4s, %s %s %s\n\n", salesperson.getAddress().getStreet(), salesperson.getAddress().getCity(), 
				salesperson.getAddress().getState(), salesperson.getAddress().getZip(), salesperson.getAddress().getCountry());
		
		//Sale information
		System.out.printf("Item%68s\n", "Total");
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-                          -=-=-=-=-=-");

		Double subtotal = 0.0;
		Double fullTax = 0.0;
		for (Item i : this.getItems()) {
			System.out.printf("%-61s$%10.2f\n    ", i.getName(), i.getTotalCost());
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("(%s #%s", i.getTypeName(), i.getCode()));
			if (i.getType().equals("PN") || i.getType().equals("PU")) {
				sb.append(String.format(" @$%.2f/ea)", i.getCost()));
			} else if (i.getType().equals("PG")){
				sb.append(")");
			} else if (i.getType().equals("SV")) {
				String employeeName = (((ServiceSale) i).getEmployee().getLastName()+", "+((ServiceSale) i).getEmployee().getFirstName());
				sb.append(String.format(" by %s %.2fhrs@$%.2f/hr)", employeeName, ((ServiceSale) i).getNumberOfHours(),
						((ServiceSale) i).getHourlyRate(), null));
			} else if (i.getType().equals("SB")) {
				Double time = ((SubscriptionSale) i).getDaysBetween();
				sb.append(String.format(" %.0f days @$%.2f/yr)", time, ((SubscriptionSale) i).getAnnualFee()));
			}
			
			System.out.printf("%s\n", sb.toString());
			subtotal += i.getCost();
			fullTax += i.getTax();
			
		}
		
		Double fullDiscount = (subtotal + fullTax) * customer.getDiscount();
		fullDiscount = Math.round(fullDiscount * 100.0) / 100.0;
		String discountString = String.format("Discount (%.2f)", customer.getDiscount() * 100.0);
		
		Double fullGrandTotal = subtotal + fullTax - fullDiscount;
		
		System.out.printf("%72s\n", "-=-=-=-=-=-");
		System.out.printf("%60s $%10.2f\n", "Subtotal", subtotal);
		System.out.printf("%60s $%10.2f\n", "Tax", fullTax);
		System.out.printf("%60s $%10.2f\n", discountString, fullDiscount);
		System.out.printf("%60s $%10.2f\n", "Grand total", fullGrandTotal);
	} 
	
}
