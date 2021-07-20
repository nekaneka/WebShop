package com.imse.shopin.mongoRepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.imse.shopin.mongo.MongoEmployee;

@Repository
public interface IEmployeeMongoRepository  extends MongoRepository<MongoEmployee, String>{

	MongoEmployee findByEmail(String email);

}
