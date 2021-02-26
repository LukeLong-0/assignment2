package com.mgg;

public class Store {
	
	private String storeCode;
	private String managerCode;
	private Address address;

	public Store(String storeCode, String managerCode, Address address) {
		super();
		this.storeCode = storeCode;
		this.managerCode = managerCode;
		this.address = address;
	}
	
	public String getStoreCode() {
		return storeCode;
	}
	
	public String getManagerCode() {
		return managerCode;
	}
	
	public Address getAddress() {
		return address;
	}
		
}
