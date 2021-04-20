package com.mgg;

import java.util.ArrayList;
import java.util.List;

//Main driver, calls different methods that prints
//reports.
public class SalesReport {

	public static void main(String[] args) {
		List<Sale> sales = new ArrayList<Sale>();
		sales = LoadDataSQL.getAllSales(); // list of all sales

		List<Person> employees = new ArrayList<>();
		employees = LoadDataSQL.getAllEmployees(); // list of all employees

		List<Store> stores = new ArrayList<Store>();
		stores = LoadDataSQL.getAllStores(); // list of all stores

		Salesperson.salespersonReport(sales, employees);
		Store.storeReport(sales, stores);

	}
}
