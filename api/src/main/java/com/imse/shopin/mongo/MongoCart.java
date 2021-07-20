package com.imse.shopin.mongo;

import java.util.HashMap;

public class MongoCart {

	private Double totalPrice = 0.;
	private HashMap<String, Integer> cartItems = new HashMap<>();
	
	public MongoCart() {} 
	
	
	public void addMongoItemToCart(MongoItem item) {
		
		if(cartItems.containsKey(item.getId())) 
			cartItems.put(item.getId(), cartItems.get(item.getId()) + 1);
		
		else cartItems.put(item.getId(), 1);
			
		totalPrice += item.getPrice();	
	}
	
	public void reduceMongoItemFromCart(MongoItem item) {
		
		cartItems.put(item.getId(), cartItems.get(item.getId()) - 1);
		
		if(cartItems.get(item.getId()) == 0)
			cartItems.remove(item.getId());
		
		totalPrice -= item.getPrice();
	}
	
	public HashMap<String, Integer> getCartItems() {
		return cartItems;
	}
	
	public Double getTotalPrice() {
		return totalPrice;
	}


	public void emptyCart() {
		cartItems.clear();
		totalPrice = 0.0;
	}
	
}
