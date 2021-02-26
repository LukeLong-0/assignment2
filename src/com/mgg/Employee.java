package com.mgg;

import java.util.Set;

public class Employee extends Person {

	public Employee(String personCode, char membershipCode, String lastName, String firstName, Address address,
			Set<String> emails) {
		super(personCode, membershipCode, lastName, firstName, address, emails);
	}

	@Override
	public String getMembershipType() {
		return "Employee";
	}
	
	@Override
	public Double getDiscount() {
		return .15;
	}
	
}
