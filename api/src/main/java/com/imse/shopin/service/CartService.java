package com.imse.shopin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imse.shopin.model.Cart;
import com.imse.shopin.repository.ICartRepository;

@Service
public class CartService {

	@Autowired
	private ICartRepository repository; 
	
	public Cart saveCart(Cart cart) {
		return repository.save(cart);
	}
}
