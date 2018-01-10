package com.pky.spring.mongo.controller;

import com.pky.spring.mongo.entity.User;
import com.pky.spring.mongo.repository.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class UserController{

	@Autowired
	private UserMongoRepository userMongoRepository;

	public boolean addUserToMongoDb(User user){
		try{
			userMongoRepository.save(user);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public User getUserByUserId(int userId){
		return userMongoRepository.findFirstByUserId(userId);
	}

	public User getUserByMobNumber(String mobNumber){
		return userMongoRepository.findByMobNumber(mobNumber);
	}
}