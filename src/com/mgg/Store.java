package com.mgg;

import java.util.HashMap;
import java.util.List;

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

	// Prints out a detailed report of all the stores,
	// and also calls the salesReport() method on a loop.
	public static void storeReport(List<Sale> sales, List<Store> stores) {
		int totalSales = 0;
		int fullSaleCount = 0; // Total number of sales among all salespeople
		Double grandTotal = 0.0; // Grand total for an individual salesperson
		Double fullGrandTotal = 0.0; // Grand total among all salespeople

		HashMap<Store, Integer> saleCount = new HashMap<Store, Integer>();
		HashMap<Store, Double> grandTotalCount = new HashMap<Store, Double>();

		// Add employees to employee list
		for (Store st : stores) {
			for (Sale s : sales) {
				if (s.getStoreCode().equals(st.getStoreCode())) {
					totalSales++;
					fullSaleCount++;
					grandTotal += s.getTotalCost();
					grandTotal = Math.round(grandTotal * 100.0) / 100.0;
				}
				saleCount.put(st, totalSales);
				grandTotalCount.put(st, grandTotal);
			}
			fullGrandTotal += grandTotal;
			fullGrandTotal = Math.round(fullGrandTotal * 100.0) / 100.0;
			totalSales = 0;
			grandTotal = 0.0;
		}

		System.out.println("+--------------" + "--------------------------------------------------+");
		System.out.println("| Store Sales " + "Summary Report                                     |");
		System.out.println("+--------------" + "--------------------------------------------------+");
		System.out.println("Store      Manager                        # Sales    Grand Total");

		String codeAndName;
		for (Store s : stores) {

			codeAndName = (s.getManager().getLastName() + ", " + s.getManager().getFirstName());
			System.out.printf("%-11s%-31s%-11d$%10.2f\n", s.getStoreCode(), codeAndName, saleCount.get(s),
					grandTotalCount.get(s));
		}

		System.out.println("+----------------------------------------------------------------+");
		System.out.printf("%43d          $%10.2f\n", fullSaleCount, fullGrandTotal);

		for (Sale s : sales) {
			s.saleReport();
		}
	}
}
