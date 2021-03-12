package com.mgg;

import java.util.ArrayList;
import java.util.List;

//Models an employee, a type of person

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
	
	public static void salespersonReport() {
		
		List<Person> people = new ArrayList<Person>();//List of all people
		people = LoadData.parsePersonFile();
		
		List<Person> employees = new ArrayList<Person>();//List of all employees
		
		for (Person p : people) { //Add employees to employee list
			if (Character.compare(p.getMembershipCode(), 'E') == 0) {
				employees.add(new EmployeeSale(p.getPersonCode(), p.getMembershipCode(),
						p.getLastName(), p.getFirstName(), p.getAddress(), p.getEmails(), 0, 0.0));
			}
		}
		
		//TODO: ADD LOOP TO FILL EMPLOYEE LIST WITH INFO ON SALES AND GRAND TOTAL! LOL
		//
		
		
		System.out.println("+-----------------------------------------------------+");
		System.out.println("| Salesperson Summary Report                          |");
		System.out.println("+-----------------------------------------------------+");
		System.out.println("Salesperson                    # Sales    Grand Total");
		
		for (Person p : employees) {
			
			System.out.println(p.getLastName() + ", " + p.getFirstName());
			
		}
		
		
	}
	
}
