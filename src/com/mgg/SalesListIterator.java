package com.mgg;

import java.util.Iterator;

public class SalesListIterator<T> implements Iterator<T> {

	SaleNode<T> current;
	
	public SalesListIterator(SalesList<T> list) {
		current = list.getHead();
	}
	
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public T next() {
		T sale = current.getSale();
		current = current.getNext();
		return sale;
	}
	
	
}
