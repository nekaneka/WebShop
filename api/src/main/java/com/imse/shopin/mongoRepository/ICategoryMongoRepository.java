package com.imse.shopin.mongoRepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.imse.shopin.mongo.MongoCategory;

@Repository
public interface ICategoryMongoRepository extends MongoRepository<MongoCategory, String>{

}
