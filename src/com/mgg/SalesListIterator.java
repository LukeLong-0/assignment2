package com.mgg;

import java.util.Iterator;

public class SalesListIterator implements Iterator<SaleNode> {

	SaleNode current;
	
	public SalesListIterator(SalesList list) {
		current = list.getHead();
	}
	
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public SaleNode next() {
		SaleNode sale = current;
		current = current.getNext();
		return sale;
	}
	
	
}
