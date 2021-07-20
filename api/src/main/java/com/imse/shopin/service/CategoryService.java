package com.imse.shopin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imse.shopin.model.Category;
import com.imse.shopin.model.User;
import com.imse.shopin.repository.ICategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private ICategoryRepository repository; 
	
	public void saveCategory(Category category) {
		repository.save(category);
	}

	public void saveCategories(List<Category> list) {
		repository.saveAll(list);
	}
	
	public List<Category> findAllCategories() {
		return repository.findAll();
	}

	
}
