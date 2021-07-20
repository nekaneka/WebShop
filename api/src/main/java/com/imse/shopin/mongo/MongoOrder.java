package com.imse.shopin.mongo;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Orders")
public class MongoOrder {

	@Id	private String orderId;
	private Date orderDate; 
	private double totalPrice;
	private HashMap<String, Integer> orderItems;
	
	public MongoOrder() {	}

	public MongoOrder(String orderId, Date orderDate, HashMap<String, Integer> orderItems, double price) {
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderItems = orderItems;
		this.totalPrice = price; 
	}
	
	public MongoOrder(Date date, HashMap<String, Integer> orderItems, double price) {
		this.orderId = UUID.randomUUID().toString();
		this.orderDate = date; 
		this.orderItems = new HashMap<String, Integer>();
		this.orderItems.putAll(orderItems); 
		this.totalPrice = price; 
	}

	public String getOrderId() {
		return orderId;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}

	public HashMap<String, Integer> getOrderItems() {
		return orderItems;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public void setOrderItems(HashMap<String, Integer> orderItems) {
		this.orderItems = orderItems;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
}
