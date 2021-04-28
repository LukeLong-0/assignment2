package com.mgg;

public class SaleNode {

	private Sale item;
	private SaleNode next;
	
	public SaleNode(Sale item) {
		this.item = item;
		this.next = null;
	}

	public SaleNode getNext() {
		return next;
	}

	public void setNext(SaleNode next) {
		this.next = next;
	}

	public Sale getItem() {
		return item;
	}
	
}
