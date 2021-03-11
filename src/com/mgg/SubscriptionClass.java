package com.mgg;

public class SubscriptionClass extends Item {

	public SubscriptionClass(String code, String type, String name, Item item, String beginDate, String endDate) {
		super(code, type, name);
		this.item = item;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	private Item item;
	private String beginDate;
	private String endDate;
	
	public Item getItem() {
		return item;
	}
	
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
