package com.mgg;

import java.util.Iterator;

public class SalesListIterator<T> implements Iterator<SaleNode<T>> {

	SaleNode<T> current;
	
	public SalesListIterator(SalesList<T> list) {
		current = list.getHead();
	}
	
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public SaleNode<T> next() {
		SaleNode<T> sale = current;
		current = current.getNext();
		return sale;
	}
	
	
}
