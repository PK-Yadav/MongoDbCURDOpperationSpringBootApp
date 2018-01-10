package com.pky.spring.mongo.service;

import com.pky.spring.mongo.controller.UserController;
import com.pky.spring.mongo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
public class SpringBootMongoWebService{

	@Autowired
	private UserController userController;

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUserToMongoDb(@RequestBody User user) {
		if (userController.addUserToMongoDb(user)) {
			return user.toString() + " Successfully added";
		}
		return "Fail to add/register User :- " + user.toString();
	}

	@RequestMapping(value = "/userById/{userId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public User getUserByUserId(@PathVariable("userId") int userId){
		return userController.getUserByUserId(userId);
	}

	@RequestMapping(value = "/userByMob/{mobNumber}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public User getUserByMobNumber(@PathVariable("mobNumber") String mobNumber){
		return userController.getUserByMobNumber(mobNumber);
	}
}