package com.mgg;

import java.util.ArrayList;
import java.util.List;

//Models an employee, a type of person

import java.util.Set;

//Models an employee, a subtype of Person.

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
