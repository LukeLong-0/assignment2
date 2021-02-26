package com.mgg;

public abstract class Item {

	public Item(String code, String type, String name, Double basePrice) {
		this.code = code;
		this.type = type;
		this.name = name;
		this.basePrice = basePrice;
	}
	
	private String code;
	private String type;
	private String name;
	protected Double basePrice;
	protected Double cost;
	protected String typeName;
	
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	
	public abstract Double getCost();
	
	public Double getBasePrice() {
		return basePrice;
	}
	
	public abstract String getTypeName();
	
}
