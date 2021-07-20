package com.imse.shopin.mongoService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imse.shopin.mongo.MongoOrder;
import com.imse.shopin.mongoRepository.IOrderMongoRepository;

@Service
public class MongoOrderService {

	@Autowired
	IOrderMongoRepository repository; 
	
	
	public void insertOrder(MongoOrder order) {
		repository.insert(order);
	}
	
	public List<MongoOrder> getAllOrders(){
		return repository.findAll(); 
	}
	
	public List<MongoOrder> getOrdersBetween(Date from, Date to){
		return repository.findByOrderDateBetween(from, to);
	}
}
