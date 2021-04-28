package com.mgg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SalesListTest {

	public static void main(String[] args) {
		
		Customer customer1 = new Customer(null, 'Z', "Smith", "Amy",
				null, null, null, null);
		Customer customer2 = new Customer(null, 'Z', "Smith", "John",
				null, null, null, null);
		Customer customer3 = new Customer(null, 'Z', "Long", "Luke",
				null, null, null, null);
		Sale sale1 = new Sale(null, null, customer1, null, null);
		Sale sale2 = new Sale(null, null, customer2, null, null);
		Sale sale3 = new Sale(null, null, customer3, null, null);
		
		SalesList<Sale> list = new SalesList<Sale>(new PersonComparator());
		
		list.add(list, sale3);
		list.add(list, sale2);
		list.add(list, sale1);
		
		for (Sale sale : list) {
			System.out.println(sale.getCustomer().getFirstName());
		}
		
	}

}
