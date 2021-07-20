package com.imse.shopin.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Items")
public class MongoItem {

	@Id
	private String id;
	
	private double price; 
	private String name; 
	private String description; 
	
	public MongoItem() { }

	public MongoItem(String id, double price, String name, String description) {
		this.id = id;
		this.price = price;
		this.name = name;
		this.description = description;
	}

	public MongoItem(double price, String name, String description) {
		this.price = price;
		this.name = name;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public double getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MongoItem other = (MongoItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
	
	
}
