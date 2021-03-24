package com.mgg;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

//Models an instance of a subscription
//in the context of a sale

public class SubscriptionSale extends Subscription {

	public SubscriptionSale(String code, String type, String name, Double annualFee, LocalDate beginDate, LocalDate endDate) {
		super(code, type, name, annualFee);
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	private LocalDate beginDate;
	private LocalDate endDate;
		
	public LocalDate getBeginDate() {
		return beginDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}

	@Override
	public String getTypeName() {
		return "Subscription";
	}

	@Override
	public Double getCost() {
		Double result = (this.getDaysBetween() / 365.0) * this.getAnnualFee();

		result = Math.round(result * 100.0) / 100.0;
		
		return result;
	}
	
	public Double getDaysBetween() {
		Double result = ((this.beginDate.until(this.endDate, ChronoUnit.DAYS)+ 1l)/1.0);
		
		return result;
	}
	
}
