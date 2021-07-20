package com.imse.shopin.mongo;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class MongoUser {
	
	@Id
	private String id; 
	private String email; 
	private String password;
	private MongoCart cart; 
	
	private Set<MongoOrder> orders;
	
	private String name; 
	private String iban; 
	private String address; 
	private boolean isLogedIn; 
	
	public MongoUser() {}

	public MongoUser(String id, String email, String password, String name, String iban, String address, boolean isLogedIn) {
		this.id = id;
		this.email = email; 
		this.password = password; 
		this.name = name;
		this.iban = iban;
		this.address = address;
		this.isLogedIn = isLogedIn;
		this.cart = new MongoCart();
		this.orders = new HashSet<MongoOrder>();
	}
	
	public MongoUser(String email, String password, String name) {
		this.id = UUID.randomUUID().toString();
		this.email = email; 
		this.password = password; 
		this.name = name;
		this.isLogedIn = false;
		this.cart = new MongoCart();
		this.orders = new HashSet<MongoOrder>();
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public MongoCart getCart() {
		return cart;
	}

	public Set<MongoOrder> getOrders() {
		return orders;
	}

	public String getName() {
		return name;
	}

	public String getIban() {
		return iban;
	}

	public String getAddress() {
		return address;
	}

	public boolean isLogedIn() {
		return isLogedIn;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setLogedIn(boolean isLogedIn) {
		this.isLogedIn = isLogedIn;
	}
	
	public void update(MongoOrder order) {
		this.orders.add(order);
	}

}
