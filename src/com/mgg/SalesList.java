package com.mgg;

import java.util.Comparator;
import java.util.Iterator;

public class SalesList implements Iterable<SaleNode> {
	
	private SaleNode head;
	int size;
	private Comparator<Person> personComp;
	private Comparator<Double> doubleComp;
	String compType;
	
	public SalesList(String compType) {
		this.head = null;
		this.size = 0;
		this.compType = compType;
	}
	
	public int getSize() {
		return size;
	}
	
	public SaleNode getHead() {
		return head;
	}
	
	public Comparator<SaleNode> getComparator() {
		return comp;
	}

	public SalesList add(SalesList list, Sale sale) {
		
		Comparator<SaleNode> comp = list.getComparator();
		
		SaleNode curr = new SaleNode(sale);
		curr.setNext(null);
		if (list.head == null) {
			list.head = curr;
		}
		else {
			
			for (SaleNode sn : list) {

				if (list.compType.equals("person")) {
					Person one = sn.getItem().getCustomer();
					Person two = sn.getNext().getItem().getCustomer();
					
					
					
				}
				
			}
			
		}
		return null;
	}

	public Iterator<SaleNode> iterator() {
		return new SalesListIterator(this);
	}

}

class PersonComparator implements Comparator<Person> {

	@Override
	public int compare(Person o1, Person o2) {
		int result = (o1.getLastName().compareTo(o2.getLastName()));
		if (result == 0) {
			result = (o1.getFirstName().compareTo(o2.getFirstName()));
		}
		return result;
	}
	
}
