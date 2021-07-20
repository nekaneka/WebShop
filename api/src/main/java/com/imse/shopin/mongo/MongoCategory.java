package com.imse.shopin.mongo;


import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Categories")
public class MongoCategory {

	
	@Id
	private String id;
	private String name; 
	private String description; 
	private Set<MongoItem> items;
	
	public MongoCategory(String id, String name, String description, Set<MongoItem> items) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.items = items;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Set<MongoItem> getItems() {
		return items;
	}

	public void setItems(Set<MongoItem> items) {
		this.items = items;
	} 
	
	public void addItem(MongoItem item) {
		items.add(item);
	}
	
	 
}
