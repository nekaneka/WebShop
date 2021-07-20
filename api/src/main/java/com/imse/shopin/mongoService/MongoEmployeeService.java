package com.imse.shopin.mongoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imse.shopin.mongo.MongoEmployee;
import com.imse.shopin.mongoRepository.IEmployeeMongoRepository;

@Service
public class MongoEmployeeService {
	
	MongoEmployee currentEmployee = null; 

	@Autowired
	IEmployeeMongoRepository repository; 
	
	public void insertNewEmployee(MongoEmployee employee) {
		repository.insert(employee);
	}
	
	public MongoEmployee findByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	public MongoEmployee getCurrentEmployee() {
		return currentEmployee;
	}
	
	public void setCurrentEmployee(MongoEmployee currentEmployee) {
		this.currentEmployee = currentEmployee;
	}
}

