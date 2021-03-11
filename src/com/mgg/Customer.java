package com.mgg;

//Models a customer, a type of person

import java.util.Set;

public class Customer extends Person {

	public Customer(String personCode, char membershipCode, String lastName, String firstName, Address address,
			Set<String> emails) {
		super(personCode, membershipCode, lastName, firstName, address, emails);
	}
	
	@Override
	public String getMembershipType() {
		return "Customer";
	}
	
	@Override
	public Double getDiscount() {
		return 0.0;
	}
	
}
