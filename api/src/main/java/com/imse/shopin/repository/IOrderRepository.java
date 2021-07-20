package com.imse.shopin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.imse.shopin.model.Order;
import com.imse.shopin.model.User;

public interface IOrderRepository extends JpaRepository<Order, String> {
	
	List<Order> findByuser(User user);
}
