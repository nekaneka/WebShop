package com.imse.shopin.mongoRepository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.imse.shopin.mongo.MongoOrder;

@Repository
public interface IOrderMongoRepository extends MongoRepository<MongoOrder, String>{

	List<MongoOrder> findByOrderDateBetween(Date from, Date to);

}
