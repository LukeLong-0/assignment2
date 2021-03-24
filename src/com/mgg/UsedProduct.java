package com.mgg;

//Models a Used Product, a type of item


public class UsedProduct extends Item {
	
	public Double basePrice;

	public UsedProduct(String code, String type, String name, Double basePrice) {
		super(code, type, name);
		this.basePrice = basePrice;
	}
	
	public Double getBasePrice() {
		return basePrice;
	}
	
	@Override
	public String getTypeName() {
		return "Used Product";
	}
	
	@Override
	public Double getCost() {
		return null;
	}

	@Override
	public Double getTaxRate() {
		return 0.0725;
	}
	
}
