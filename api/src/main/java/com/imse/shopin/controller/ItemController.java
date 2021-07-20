package com.imse.shopin.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imse.shopin.mongoService.MongoItemService;
import com.imse.shopin.service.ItemService;

@CrossOrigin
@RestController
@RequestMapping("/items")
public class ItemController {
	
	@Autowired
	private ItemService service; 
	
	@Autowired
	private MongoItemService mongoItemService; 
	
	@Autowired 
	private Migrator migrator; 
	
	@CrossOrigin
	@GetMapping("/all")
	public List<?> getAllItems(){
		if(migrator.isMigrated()) return mongoItemService.findAllItems();
		return service.findAllItems();
	}

}
