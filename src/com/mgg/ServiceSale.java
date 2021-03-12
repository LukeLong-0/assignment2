package com.mgg;

public class ServiceSale extends Item {

	public ServiceSale(String code, String type, String name, String employeeCode, Double numberOfHours) {
		super(code, type, name);
		this.employeeCode = employeeCode;
		this.numberOfHours = numberOfHours;
	}


	private String employeeCode;
	private Double numberOfHours;
	
	public String getEmployeeCode() {
		return employeeCode;
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
		return null;
	}
	
}
