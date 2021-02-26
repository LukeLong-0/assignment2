package com.mgg;

public class UsedProduct extends Item {

	public UsedProduct(String code, String type, String name, Double basePrice, Double cost, String typeName) {
		super(code, type, name, basePrice);
		this.cost = cost;
		this.typeName = typeName;
	}

	@Override
	public String getTypeName() {
		return "UsedProduct";
	}
	
	@Override
	public Double getCost() {
		return (this.getBasePrice() * .8) + (this.getBasePrice() * 0.0725);
	}
	
}
