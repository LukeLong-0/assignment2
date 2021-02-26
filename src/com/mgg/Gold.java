package com.mgg;

import java.util.Set;

public class Gold extends Person {
	
	public Gold(String personCode, char membershipCode, String lastName, String firstName, Address address,
			Set<String> emails) {
		super(personCode, membershipCode, lastName, firstName, address, emails);
		
	}

	@Override
	public String getMembershipType() {
		return "GoldCustomer";
	}
	
	@Override
	public Double getDiscount() {
		return .05;
	}

}
