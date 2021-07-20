package com.imse.shopin.mongoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imse.shopin.mongo.MongoUser;
import com.imse.shopin.mongoRepository.IUserMongoRepository;

@Service
public class MongoUserService {
	
	private MongoUser currentMongoUser = null; 

	@Autowired
	private IUserMongoRepository repository; 
	
	public void insertMongoUser(MongoUser user) {
		repository.insert(user);
	}
	
	public void updateMongoUser(MongoUser user) {
		repository.save(user);
	}
	
	public List<MongoUser> getAllMongoUsers(){
		return repository.findAll();
	}

	public MongoUser getMongoUserByMail(String email) {
		return repository.findByemail(email);
		
	}
	
	public MongoUser getCurrentMongoUser() {
		return currentMongoUser;
	}
	
	public void setCurrentMongoUser(MongoUser currentMongoUser) {
		this.currentMongoUser = currentMongoUser;
	}

	public List<MongoUser> findAllUser() {
		return repository.findAll();
	}
}
