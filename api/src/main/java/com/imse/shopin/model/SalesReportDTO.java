package com.imse.shopin.model;

public class SalesReportDTO {

	private String userName; 
	private String itemName; 
	private int timesBought; 
	
	public SalesReportDTO(String uName, String iName, int nr) {
		this.userName = uName; 
		this.itemName = iName; 
		this.timesBought = nr; 
	}

	public String getUserName() {
		return userName;
	}

	public String getItemName() {
		return itemName;
	}

	public int getTimesBought() {
		return timesBought;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setTimesBought(int timesBought) {
		this.timesBought = timesBought;
	}
	
	
}
