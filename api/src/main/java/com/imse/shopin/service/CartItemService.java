package com.imse.shopin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imse.shopin.model.Cart;
import com.imse.shopin.model.CartItems;
import com.imse.shopin.repository.ICartItemsRepository;

@Service
public class CartItemService {

	@Autowired
	private ICartItemsRepository repository; 
	
	public CartItems addItemToCart(CartItems cartItem) {
		return repository.save(cartItem);
	}
	
	public List<CartItems> getCartItemsbyCart(Cart cart) {
		return repository.findAllCarItems(cart);
	}
	
	public void deleteCartitem(CartItems cartItem) {
		repository.delete(cartItem);
	}

	public void increaseQuantity(CartItems itemInCart) {
		CartItems itemToUpdate = repository.getOne(itemInCart.getId());
		itemToUpdate.increaseQuantity();
		repository.save(itemToUpdate);
	}
	
	public void decreaseQuantity(CartItems itemInCart) {
		CartItems itemToUpdate = repository.getOne(itemInCart.getId());
		itemToUpdate.decreaseQuantity();
		
		if(itemToUpdate.getQuantity() != 0) repository.save(itemToUpdate);
		else repository.delete(repository.getOne(itemInCart.getId()));
	}
}
