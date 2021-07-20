package com.imse.shopin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imse.shopin.model.Order;
import com.imse.shopin.model.User;
import com.imse.shopin.repository.IOrderRepository;

@Service
public class OrderService {

	@Autowired
	IOrderRepository repository; 
	
	public Order saveItem(Order order) {
		return repository.save(order);
	}

	public Order findOrder(String orderId) {
		return repository.findById(orderId).orElse(null);
	}
	
	public List<Order> findAllOrders(){
		return repository.findAll(); 
	}
	
	public List<Order> findOrderbyuser(User user) {
		return (List<Order>) repository.findByuser(user);
	}
	
	public void saveOrders(List<Order> orders) {
		repository.saveAll(orders);
	}
}
