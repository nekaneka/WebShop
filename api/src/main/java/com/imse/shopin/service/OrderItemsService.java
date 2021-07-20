package com.imse.shopin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imse.shopin.model.Order;
import com.imse.shopin.model.OrderItems;
import com.imse.shopin.repository.IOrderItemsRepository;

@Service
public class OrderItemsService {

	@Autowired
	IOrderItemsRepository repository; 
	
	public OrderItems saveOrder(OrderItems order) {
		return repository.save(order); 
	}
	
	public List<OrderItems> findAllOrderItems() {
		return repository.findAll() ;
	}

	public void saveOrderItems(List<OrderItems> asList) {
		repository.saveAll(asList);
	}
	
	public List<OrderItems> findOrderItemByOrder(Order order){
		return (List<OrderItems>) repository.findOrderByorder(order);
	}
}
