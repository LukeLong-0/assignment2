package com.mgg;

//Models a customer, a type of person

import java.util.Set;

public class Customer extends Person {

	public Customer(String personCode, char membershipCode, String lastName, String firstName, Address address,
			Set<String> emails, String membershipType, Double discount) {
		super(personCode, membershipCode, lastName, firstName, address, emails);
		this.membershipType = membershipType;
		this.discount = discount;		
	}
	
	@Override
	public String getMembershipType() {
		return membershipType;
	}
	
	@Override
	public Double getDiscount() {
		return discount;
	}
	
}
