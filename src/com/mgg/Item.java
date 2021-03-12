package com.mgg;

import java.util.ArrayList;
import java.util.List;

//Models an item (there are many subtypes of Item)

public abstract class Item {
	
	private String code;
	private String type;
	private String name;
	private String typeName;
	private Double cost;

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
	
		
}
