package com.pky.spring.mongo.controller;

import com.pky.spring.mongo.entity.User;
import com.pky.spring.mongo.repository.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class UserController{

	@Autowired
	private UserMongoRepository userMongoRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	public boolean addUserToMongoDb(User user){
		try{
			userMongoRepository.save(user);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateUser(User user){
		try{
			userMongoRepository.save(user);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateUserFirstname(String firstName, String newFName){
		try{

			Query query = new Query();
			query.addCriteria(Criteria.where("firstName").is(firstName));

			Update update = new Update();
			update.set("firstName",newFName);
			mongoTemplate.updateFirst(query, update, User.class);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deleteUserById(int userId){
		try {
			userMongoRepository.delete(userId);
		}catch (Exception ex){
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

	public List<User> getAllUserOfName(String firstName){
		Query query = new Query();
		query.addCriteria(Criteria.where("firstName").is(firstName));

		return mongoTemplate.find(query, User.class);
	}
}