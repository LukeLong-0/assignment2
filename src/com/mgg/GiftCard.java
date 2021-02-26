package com.mgg;

public class GiftCard extends Item {
	
	public Double basePrice;

	public GiftCard(String code, String type, String name) {
		super(code, type, name);
		this.basePrice = basePrice;
	}
	
	public Double getBasePrice() {
		return basePrice;
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
