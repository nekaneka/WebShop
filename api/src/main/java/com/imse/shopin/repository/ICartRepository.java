package com.imse.shopin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imse.shopin.model.Cart;

public interface ICartRepository extends JpaRepository<Cart, String>{

}
