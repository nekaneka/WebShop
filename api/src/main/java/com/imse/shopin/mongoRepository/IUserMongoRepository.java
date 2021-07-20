package com.imse.shopin.mongoRepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.imse.shopin.mongo.MongoUser;

@Repository
public interface IUserMongoRepository extends MongoRepository<MongoUser, String> {

	MongoUser findByemail(String email);

}
