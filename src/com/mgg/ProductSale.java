package com.mgg;

//Used or new products that go into Sale.java's item list

public class ProductSale extends Item {

	public ProductSale(String code, String type, String name, int quantity) {
		super(code, type, name);
		this.quantity = quantity;
	}
	
	private int quantity;
		
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
