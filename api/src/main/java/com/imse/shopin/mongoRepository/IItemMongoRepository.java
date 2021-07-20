package com.imse.shopin.mongoRepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.imse.shopin.mongo.MongoItem;

@Repository
public interface IItemMongoRepository extends MongoRepository<MongoItem, String>{

}
