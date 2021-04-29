package com.mgg;

public class SaleNode<T> {

	private final T sale;
	private SaleNode<T> next;
	private SaleNode<T> previous;
	
	public SaleNode(T sale) {
		this.sale = sale;
		this.next = null;
		this.previous = null;
	}
	
	public SaleNode<T> getNext() {
		return next;
	}

	public void setNext(SaleNode<T> next) {
		this.next = next;
	}

	public SaleNode<T> getPrevious() {
		return previous;
	}

	public void setPrevious(SaleNode<T> previous) {
		this.previous = previous;
	}
	
	public T getSale() {
		return sale;
	}
}
