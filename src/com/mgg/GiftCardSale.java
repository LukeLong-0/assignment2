package com.mgg;

public class GiftCardSale extends Item{

	public GiftCardSale(String code, String type, String name, Item item, Double cardAmount) {
		super(code, type, name);
		this.item = item;
		this.cardAmount = cardAmount;
	}
	
	private Item item;
	private Double cardAmount;
	
	public Item getItem() {
		return item;
	}
	
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
