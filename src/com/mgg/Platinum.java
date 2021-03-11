package com.mgg;

//Models the platinum tier membership of Modern Geek Gaming

import java.util.Set;

public class Platinum extends Person {

	public Platinum(String personCode, char membershipCode, String lastName, String firstName, Address address,
			Set<String> emails) {
		super(personCode, membershipCode, lastName, firstName, address, emails);
	}

	@Override
	public String getMembershipType() {
		return "PlatinumCustomer";
	}
	
	@Override
	public Double getDiscount() {
		return .1;
	}
	
}
