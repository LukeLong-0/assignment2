package com.mgg;

//Models an instance of a new product
//in the context of a sale

public class NewProductSale extends NewProduct {

	public NewProductSale(String code, String type, String name, Double basePrice, int quantity) {
		super(code, type, name, basePrice);
		this.quantity = quantity;
	}
	
	private int quantity;
			
	public int getQuantity() {
		return quantity;
	}
	
	
	@Override
	public Double getCost() {
		Double result = this.basePrice * this.quantity;
		result =  Math.round(result * 100.0) / 100.0;
		return result;
	}
	
}
