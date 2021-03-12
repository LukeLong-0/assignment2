package com.mgg;

public class SubscriptionSale extends Item {

	public SubscriptionSale(String code, String type, String name, String beginDate, String endDate) {
		super(code, type, name);
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	private String beginDate;
	private String endDate;
		
	public String getBeginDate() {
		return beginDate;
	}
	
	public String getEndDate() {
		return endDate;
	}

	@Override
	public String getTypeName() {
		return "Subscription";
	}

	@Override
	public Double getCost() {
		return null;
	}
	
	
	
}
