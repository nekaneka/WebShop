package com.imse.shopin.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table( name = "category")
public class Category {

	@Id
	@Column(name = "category_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int categoryId;
	
	@Column(name = "category_name")
	private String categoryName; 
	
	@Column(name = "category_description")
	private String description; 
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "category_item", 
			joinColumns = @JoinColumn(name = "category_id"), 
			inverseJoinColumns = @JoinColumn(name = "id"))
	private Set<Item> items = new HashSet<>();
	
	
	public Category() {}

	
	public Category(String categoryName, String description) {
		this.categoryName = categoryName;
		this.description = description; 
	}

	public Category(String categoryName, String description, Set<Item> items) {
		this.categoryName = categoryName;
		this.description = description; 
		this.items = items;
	}




	public int getCategoryId() {
		return categoryId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public Set<Item> getItems() {
		return items;
	}
	
	public void addItemToCategeory(Item item) {
		items.add(item);
	}
	
	public void addItemsTocategory(List<Item> items) {
		items.addAll(items);
	}
	
	public String getDescription() {
		return description;
	}
	@Override
	public boolean equals(Object obj) {
		
		if (obj == this) return true; 
        if (!(obj instanceof Category)) return false; 
         
        Category c = (Category) obj; 

        return Integer.compare(this.categoryId, c.categoryId) == 0
                && categoryName.equals(c.categoryName); 
	}
	
}
