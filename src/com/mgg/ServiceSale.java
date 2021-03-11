package com.mgg;

public class ServiceSale extends Item {

	public ServiceSale(String code, String type, String name, Item item, String employeeCode, Double numberOfHours) {
		super(code, type, name);
		this.item = item;
		this.employeeCode = employeeCode;
		this.numberOfHours = numberOfHours;
	}

	private Item item;
	private String employeeCode;
	private Double numberOfHours;
	
	public Item getItem() {
		return item;
	}

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
