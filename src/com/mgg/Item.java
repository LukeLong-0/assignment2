package com.mgg;

//Models an item (there are many subtypes of Item)

public abstract class Item {
	
	private String code;
	private String type;
	private String name;

	public Item(String code, String type, String name) {
		this.code = code;
		this.type = type;
		this.name = name;
	}

	public String getCode() {
		return code;
	}
	
	public String getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract String getTypeName();
	
	public abstract Double getCost();
	
	public Double getTotalCost() {
		return Math.round((this.getCost() + this.getTax()) * 100.0) / 100.0;
	}
	
	public abstract Double getTaxRate();
	
	public Double getTax() {
		return Math.round((this.getCost() * this.getTaxRate()) * 100.0) / 100.0;
	}
		
}
