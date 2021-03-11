package com.mgg;

//Models a Modern Geek Gaming store

public class Store {
	
	private String storeCode;
	private Person manager;
	private Address address;

	public Store(String storeCode, Person manager, Address address) {
		super();
		this.storeCode = storeCode;
		this.manager = manager;
		this.address = address;
	}
	
	public String getStoreCode() {
		return storeCode;
	}
	
	public Person getManager() {
		return manager;
	}
	
	public Address getAddress() {
		return address;
	}
		
}
