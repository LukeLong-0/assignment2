package com.mgg;

public class NewProduct extends Item {

	public NewProduct(String code, String type, String name, Double basePrice, Double cost, String typeName) {
		super(code, type, name, basePrice);
		this.cost = cost;
		this.typeName = typeName;
	}
	
	@Override
	public String getTypeName() {
		return "NewProduct";
	}
	
	@Override
	public Double getCost() {
		return this.getBasePrice() + (this.getBasePrice() * 0.0725);
	}
	
}
