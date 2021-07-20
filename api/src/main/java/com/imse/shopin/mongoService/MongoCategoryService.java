package com.imse.shopin.mongoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imse.shopin.mongo.MongoCategory;
import com.imse.shopin.mongoRepository.ICategoryMongoRepository;

@Service
public class MongoCategoryService {
	
	@Autowired
	private ICategoryMongoRepository repository; 

	
	public void insertMongoCategory(MongoCategory category) {
		repository.insert(category);
	}
	
	public void updateCategory(MongoCategory category) {
		repository.save(category); 
	}
	
	public List<MongoCategory> getAllMongoCategories(){
		return repository.findAll();
	}

}
