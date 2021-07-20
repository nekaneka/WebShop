package com.imse.shopin.model;

import org.springframework.stereotype.Component;

@Component
public class CartDTO {

	private String itemId; 
	private String name; 
	private double oneItemPrice; 
	private int quantity; 
	private double totalPrice; 
	
	public CartDTO() {}
	
	public CartDTO(String id, String name, double price, int quantity) {
		this.itemId = id; 
		this.name = name; 
		this.oneItemPrice = price; 
		this.quantity = quantity; 
		this.totalPrice = oneItemPrice * quantity; 
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getOneItemPrice() {
		return oneItemPrice;
	}

	public void setOneItemPrice(double oneItemPrice) {
		this.oneItemPrice = oneItemPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
