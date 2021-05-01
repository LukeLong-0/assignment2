package com.mgg;

import java.util.ArrayList;
import java.util.List;

//Main driver, calls different methods that prints
//reports.
public class SalesReport {

	public static void main(String[] args) {

		List<Sale> sales = new ArrayList<Sale>();
		sales = LoadDataSQL.getAllSales(); // list of all sales
		
		SalesList<Sale> personSortedSales = new SalesList<Sale>(new PersonComparator());
		personSortedSales.batchAdd(sales); // sorted by customer last/first name

		SalesList<Sale> valueSortedSales = new SalesList<Sale>(new SaleValueComparator());
		valueSortedSales.batchAdd(sales); // sorted by value of sale

		SalesList<Sale> storeSortedSales = new SalesList<Sale>(new StoreSalespersonComparator());
		storeSortedSales.batchAdd(sales); // sorted by store code and salesperson names
		
		Sale.simpleSaleReport(personSortedSales, "Customer");
		Sale.simpleSaleReport(valueSortedSales, "Total");
		Sale.simpleSaleReport(storeSortedSales, "Store");
		
	}
}
