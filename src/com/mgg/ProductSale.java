package com.mgg;

//Used or new products that go into Sale.java's item list

public class ProductSale extends Item {

	public ProductSale(String code, String type, String name, Item item, int quantity) {
		super(code, type, name);
		this.item = item;
		this.quantity = quantity;
	}
	
	private Item item;
	private int quantity;
	
	public Item getItem() {
		return item;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	@Override
	public String getTypeName() {
		return this.getTypeName();
	}
	
	@Override
	public Double getCost() {
		return null;
	}
	
	
	
}
