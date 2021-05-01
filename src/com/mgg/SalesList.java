package com.mgg;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * A doubly linked list that can support any data type, but is only used for 
 * sales in this phase.
 * @author Luke
 *
 * @param <T>
 */
public class SalesList<T> implements Iterable<T> {
	
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
	
	public void add(T sale) {
		SaleNode<T> newNode = new SaleNode<T>(sale);
		if (this.head == null) {
			this.head = newNode;
			return;
		}
		
		SaleNode<T> curr = this.head;
		
		while (curr != null) {
			if (this.comp.compare(curr.getSale(), sale) < 0) {
				//add to end
				if (curr.getNext() == null) {
					curr.setNext(newNode);
					newNode.setPrevious(curr);
					this.size++;
					return;
				}
				else {
					curr = curr.getNext();
				}
			} else {
				// add to beginning
				if (curr.getPrevious() == null) {
					newNode.setNext(curr);
					curr.setPrevious(newNode);
					this.head = newNode;
					this.size++;
					return;
				}
				else {
					newNode.setNext(curr);
					newNode.setPrevious(curr.getPrevious());
					curr.getPrevious().setNext(newNode);
					curr.setPrevious(newNode);
					this.size++;
					return;
				}
			}
		}
	}
	
	public void remove(SaleNode<T> node) {
		if (head == null || node == null) {
			return;
		}
		if (head == node) {
			head.setNext(node);
		}
		if (node.getNext() != null) {
			node.getNext().setPrevious(node.getPrevious());
		}
		if (node.getPrevious() != null) {
			node.getPrevious().setNext(node.getNext());
		}
		this.size--;
		return;
	}
	
	public T getSaleByIndex(int index) {
		return this.getNode(index).getSale();
	}

	public Iterator<T> iterator() {
		return new SalesListIterator<T>(this);
	}
	
	private SaleNode<T> getNode(int index) {
		SaleNode<T> curr = head;
		for (int i=0; i<index; i++) {
			curr = curr.getNext();
		}
		return curr;
	}
	
	public void batchAdd(List<T> oldList) {
		for (T s : oldList) {
			this.add(s);
		}
	}
	
}

/**
 * A comparator that compares based off of a customer's last name, 
 * and first name if the last names are the same.
 * @author Luke
 *
 */
class PersonComparator implements Comparator<Sale> {

	public int compare(Sale o1, Sale o2) {
		int result = o1.getCustomer().getLastName().compareTo(o2.getCustomer().getLastName());
		if (result == 0) {
			result = o1.getCustomer().getFirstName().compareTo(o2.getCustomer().getFirstName());
		}
		return result;
	}
}

/**
 * A comparator that compares based off the USD price of a sale
 * @author Luke
 *
 */
class SaleValueComparator implements Comparator<Sale> {

	@Override
	public int compare(Sale o1, Sale o2) {
		return o2.getTotalCost().compareTo(o1.getTotalCost());
	}
}


/**
 * A comparator that compares based off of the store's store code,
 * and then by salesperson last/first name if the stores are the same.
 * @author Luke
 *
 */
class StoreSalespersonComparator implements Comparator<Sale> {

	@Override
	public int compare(Sale o1, Sale o2) {
		int result = o1.getStoreCode().compareTo(o2.getStoreCode());
		if (result == 0) {
			result = o1.getSalesperson().getLastName().compareTo(o2.getSalesperson().getLastName());
			if (result == 0) {
				result = o1.getSalesperson().getFirstName().compareTo(o2.getSalesperson().getFirstName());
				if (result == 0) {
					result = o1.getTotalCost().compareTo(o2.getTotalCost());
				}
			}
		}
		return result;
	}
}