package com.mgg;

import java.util.ArrayList;
import java.util.List;

public class SalesListTest {

	public static void main(String[] args) {
		
		Customer customer1 = new Customer(null, 'E', "Smith", "Amy",
				null, null, null, 0.05);
		Customer customer2 = new Customer(null, 'E', "Smith", "John",
				null, null, null, 0.05);
		Customer customer3 = new Customer(null, 'E', "Long", "Luke",
				null, null, null, 0.05);
		List<Item> items1 = new ArrayList<Item>();
		items1.add(new NewProductSale(null, null, null, 100.00, 1));
		List<Item> items2 = new ArrayList<Item>();
		items2.add(new NewProductSale(null, null, null, 60.00, 1));
		List<Item> items3 = new ArrayList<Item>();
		items3.add(new NewProductSale(null, null, null, 1.00, 1));
		
		Sale sale1 = new Sale(null, "BBB", customer1, customer2, items1);
		Sale sale2 = new Sale(null, "BBB", customer2, customer3, items2);
		Sale sale3 = new Sale(null, "AAA", customer3, customer1, items3);
		
		SalesList<Sale> list = new SalesList<Sale>(new StoreSalespersonComparator());
		
		list.add(sale3);
		list.add(sale2);
		list.add(sale1);
		
		for (Sale sale : list) {
			System.out.println(sale.getSalesperson().getFirstName());
		}
		list.remove(null);
		
	}

}