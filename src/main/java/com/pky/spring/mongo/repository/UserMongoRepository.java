package com.pky.spring.mongo.repository;

import com.pky.spring.mongo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<User, Integer>{

	User findFirstByUserId(int userId);

	User findByMobNumber(String mobNumber);
}