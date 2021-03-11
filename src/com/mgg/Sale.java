package com.mgg;

import java.util.List;

public class Sale {
	
	private String salesCode;
	private String storeCode;
	private String customerCode;
	private String salespersonCode;
	private List<Item> items;
	
	public Sale(String salesCode, String storeCode, String customerCode, 
				String salespersonCode, List<Item> items) {
		this.salesCode = salesCode;
		this.storeCode = storeCode;
		this.customerCode = customerCode;
		this.salespersonCode = salespersonCode;
		this.items = items;
	}

	public String getSalesCode() {
		return salesCode;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public String getSalespersonCode() {
		return salespersonCode;
	}

	public List<Item> getItems() {
		return items;
	}
	
}
