package com.mgg;

public class Service extends Item {
	
	private Double hourlyRate;
	
	public Service(String code, String type, String name, Double hourlyRate) {
		super(code, type, name);
		this.hourlyRate = hourlyRate;
	}

	public Double getHourlyRate() {
		return hourlyRate;
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
