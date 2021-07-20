package com.imse.shopin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.imse.shopin.model.Cart;
import com.imse.shopin.model.CartItems;

public interface ICartItemsRepository extends JpaRepository<CartItems, String> {

	@Query(value = "SELECT u FROM CartItems u WHERE u.cart = ?1")
	List<CartItems> findAllCarItems(Cart cart);
}
