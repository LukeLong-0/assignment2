package com.mgg;

//Models an instance of a used product
//in the context of a sale

public class UsedProductSale extends UsedProduct {

	public UsedProductSale(String code, String type, String name, Double basePrice, int quantity) {
		super(code, type, name, basePrice);
		this.quantity = quantity;
	}

	private int quantity;
	
	public int getQuantity() {
		return quantity;
	}
		
	@Override
	public Double getCost() {
		Double discountedBasePrice = (this.basePrice * .8);
		discountedBasePrice = Math.round(discountedBasePrice * 100.0) / 100.0;
		
		Double result = (discountedBasePrice * this.quantity);
		result = Math.round(result * 100.0) / 100.0;
		return result;
	}
			
}
