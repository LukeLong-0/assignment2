package com.mgg;

//Models an instance of a gift card
//in the context of a sale

public class GiftCardSale extends GiftCard {

	public GiftCardSale(String code, String type, String name, Double cardAmount) {
		super(code, type, name);
		this.cardAmount = cardAmount;
	}


	private Double cardAmount;
	
	public Double getCardAmount() {
		return cardAmount;
	}
		
	@Override
	public Double getCost() {
		return cardAmount;
	}
	
}
