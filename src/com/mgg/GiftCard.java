package com.mgg;

public class GiftCard extends Item {

	public GiftCard(String code, String type, String name, Double basePrice, Double cost, String typeName) {
		super(code, type, name, basePrice);
		this.cost = cost;
		this.typeName = typeName;
	}
	
	@Override
	public String getTypeName() {
		return "GiftCard";
	}
	
	@Override
	public Double getCost() {
		return null;
	}
	
}
