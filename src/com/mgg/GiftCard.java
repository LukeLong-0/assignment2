package com.mgg;

//Models a gift card, a type of item

public class GiftCard extends Item {
	
	public GiftCard(String code, String type, String name) {
		super(code, type, name);
	}
	
	@Override
	public String getTypeName() {
		return "Gift Card";
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
