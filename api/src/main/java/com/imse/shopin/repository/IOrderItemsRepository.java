package com.imse.shopin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imse.shopin.model.Order;
import com.imse.shopin.model.OrderItems;

public interface IOrderItemsRepository extends JpaRepository<OrderItems, String> {

	List<OrderItems> findOrderByorder(Order order);

}
