package com.mgg;

public class GiftCardSale extends Item{

	public GiftCardSale(String code, String type, String name, Double cardAmount) {
		super(code, type, name);
		this.cardAmount = cardAmount;
	}


	private Double cardAmount;


	
	public Double getCardAmount() {
		return cardAmount;
	}
	
	public String getTypeName() {
		return "Gift Card";
	}

	@Override
	public Double getCost() {
		return null;
	}
	
}
