package com.mgg;

//Models an instance of a Service
//in the context of a sale

public class ServiceSale extends Service {

	public ServiceSale(String code, String type, String name, Double hourlyRate, Person employee, 
			Double numberOfHours) {
		super(code, type, name, hourlyRate);
		this.employee = employee;
		this.numberOfHours = numberOfHours;
	}

	private Person employee;
	private Double numberOfHours;
	
	public Person getEmployee() {
		return employee;
	}

	public Double getNumberOfHours() {
		return numberOfHours;
	}

	@Override
	public String getTypeName() {
		return "Service";
	}
	
	@Override
	public Double getCost() {
		return Math.round((this.getHourlyRate() * this.numberOfHours) * 100.0) / 100.0;		
	}
	
}
