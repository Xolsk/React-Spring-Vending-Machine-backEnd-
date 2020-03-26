package com.vendingMachine.vendingMachine.helpers;

public class ResponseGenerator {
	
	Object object;
	int stock;
	String message;
	
	public ResponseGenerator() {}

	public ResponseGenerator(Object object, String message) {
		super();
		this.object = object;
		this.message = message;
	}

	
	public ResponseGenerator(Object object, int newStock, String message) {
		super();
		this.object = object;
		this.stock = newStock;
		this.message = message;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
