package com.imse.shopin.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imse.shopin.model.Item;
import com.imse.shopin.repository.IItemRepository;

@Service
public class ItemService {
	
	@Autowired
	IItemRepository repository; 
	
	public Item saveItem(Item item) {
		return repository.save(item);
	}

	public List<Item> saveAllItems(List<Item> items){
		return repository.saveAll(items);
	}
	
	public Item findItem(String itemId) {
		return repository.findById(itemId).orElse(null);
	}
	
	public List<Item> findAllItems(){
		return repository.findAll(); 
	}
	
	public void removeItem(String id) {
		repository.deleteById(id);
	}
}
