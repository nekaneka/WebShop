package com.imse.shopin.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item {
	

	@Id
	@Column(name="id")
	private String id;	 
	
	@Column(name="price")
	private double price; 
	
	@Column(name="name")
	private String name; 
	
	@Column(name = "description")
	private String description; 
	
	@ManyToMany(mappedBy = "items")
	private Set<Category> categories = new HashSet<>();
	
	public Item() {}
	
	public Item(double price, String name, String description) {
		this.id = UUID.randomUUID().toString();
		this.price = price; 
		this.name = name; 
		this.description = description; 
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
