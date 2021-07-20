package com.imse.shopin.model;

public class OrderItemsOfUserDTO {

	private String orderId; 
	private String itemName; 
	private int quantity; 
	private double price;
	
	public OrderItemsOfUserDTO(String orderId, String itemName, int quantity, double price) {
		this.orderId = orderId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getItemName() {
		return itemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	} 
	
	
	
}
