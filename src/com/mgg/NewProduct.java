package com.mgg;

//Models a new product, a type of item

public class NewProduct extends Item {
	
	public Double basePrice;

	public NewProduct(String code, String type, String name, Double basePrice) {
		super(code, type, name);
		this.basePrice = basePrice;
	}
	
	public Double getBasePrice() {
		return basePrice;
	}
	
	@Override
	public String getTypeName() {
		return "New Product";
	}
	
	@Override
	public Double getCost() {
		return this.basePrice + (this.basePrice * 0.0725);
	}
	
}
