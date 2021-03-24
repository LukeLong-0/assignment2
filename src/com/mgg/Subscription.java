package com.mgg;

//Models a Subscription, a type of item

public class Subscription extends Item {

	private Double annualFee;
	
	public Subscription(String code, String type, String name, Double annualFee) {
		super(code, type, name);
		this.annualFee = annualFee;
	}

	public Double getAnnualFee() {
		return annualFee;
	}

	@Override
	public String getTypeName() {
		return "Subscription";
	}
	
	@Override
	public Double getCost() {
		return null;
	}

	@Override
	public Double getTaxRate() {
		return 0.0;
	}
	
}
