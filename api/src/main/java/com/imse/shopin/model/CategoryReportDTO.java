package com.imse.shopin.model;

public class CategoryReportDTO {
	
	private String category; 
	private String itemName; 
	private int quntity;
	
	public CategoryReportDTO(String name, String itemName, int quntity) {
		this.category = name;
		this.itemName = itemName;
		this.quntity = quntity;
	}



	public String getCategory() {
		return category;
	}

	public String getItemName() {
		return itemName;
	}

	public int getQuntity() {
		return quntity;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setQuntity(int quntity) {
		this.quntity = quntity;
	}
	
	
}
