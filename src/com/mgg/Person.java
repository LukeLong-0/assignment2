package com.mgg;

import java.util.HashSet;
import java.util.Set;

public abstract class Person {
	
	private String personCode;
	protected char membershipCode;
	private String lastName;
	private String firstName;
	private Address address;
	private Set<String> emails = new HashSet<String>();
	protected String membershipType;
	protected Double discount;

	public Person(String personCode, char membershipCode, String lastName, String firstName,
				Address address, Set<String> emails) {
		super();
		this.personCode = personCode;
		this.membershipCode = membershipCode;
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.emails = emails;
	}
	
	public String getPersonCode() {
		return personCode;
	}
	
	public char getMembershipCode() {
		return membershipCode;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public Set<String> getEmails() {
		return emails;
	}
	
	public abstract String getMembershipType();
		
	public abstract Double getDiscount();
	
}
