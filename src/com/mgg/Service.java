package com.mgg;

//Models a Service, a type of item

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
	
	public Double getCost() {
		return null;
	}

	@Override
	public Double getTaxRate() {
		return 0.0285;
	}

}
