package com.mgg;

public class Store {

	public Store(String storeCode, String managerCode, Address address) {
		this.storeCode = storeCode;
		this.managerCode = managerCode;
		this.address = address;
	}
	
	private String storeCode;
	private String managerCode;
	private Address address;
	
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
