package com.imse.shopin.mongoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imse.shopin.mongo.MongoItem;
import com.imse.shopin.mongoRepository.IItemMongoRepository;

@Service
public class MongoItemService {

	@Autowired
	private IItemMongoRepository repository;
	
	public void insertItems(List<MongoItem> items) {
		repository.insert(items);
	}
	
	public void insertItem(MongoItem item) {
		repository.insert(item);
	}
	
	public MongoItem findById(String id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<MongoItem> findAllItems(){
		return repository.findAll();
	}
}
