package com.mgg;

import java.util.Set;

public class EmployeeSale extends Employee {

	public EmployeeSale(String personCode, char membershipCode, String lastName, String firstName, Address address,
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

}
