package com.mgg;

import java.util.Comparator;
import java.util.Iterator;

public class SalesList<T> implements Iterable<SaleNode<T>> {
	
	private SaleNode<T> head;
	int size;
	private final Comparator<T> comp;
	
	public SalesList(Comparator<T> comp) {
		this.head = null;
		this.size = 0;
		this.comp = comp;
	}
	
	public int getSize() {
		return size;
	}
	
	public SaleNode<T> getHead() {
		return head;
	}
	
	public void add(SalesList<T> list, T sale) {
		SaleNode<T> newNode = new SaleNode<T>(sale);
		if (list.head == null) {
			list.head = newNode;
			return;
		}
		
		SaleNode<T> curr = list.head;
		SaleNode<T> previous = null;
		int result = -1;

		//TODO: sale added is the last item of the list
		while (curr != null) {
			if (this.comp.compare(curr.getSale(), sale) < 0) {
				curr = curr.getNext();
				previous = curr.getPrevious();
				
			} else {
				newNode.setNext(curr);
				previous.setNext(newNode);
				return;
			}
			
		}
	}

	public Iterator<SaleNode<T>> iterator() {
		return new SalesListIterator<T>(this);
	}

}

class PersonComparator implements Comparator<Sale> {

	public int compare(Sale o1, Sale o2) {
		int result = (o1.getCustomer().getLastName().compareTo(o2.getCustomer().getLastName()));
		if (result == 0) {
			result = (o1.getCustomer().getFirstName().compareTo(o2.getCustomer().getFirstName()));
		}
		return result;
	}
}
