package com.pky.spring.mongo.service;

import com.pky.spring.mongo.controller.UserController;
import com.pky.spring.mongo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping(value = "/") public class SpringBootMongoWebService {

	@Autowired private UserController userController;

	@RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public String addUserToMongoDb(@RequestBody User user) {
		if (userController.addUserToMongoDb(user)) {
			return user.toString() + " Successfully added";
		}
		return "Fail to add/register User :- " + user.toString();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public String updateUserById(@RequestBody User user) {
		if (userController.updateUser(user)) {
			return user.toString() + " Successfully Updated";
		}
		return "Fail to update existing User :- " + user.toString();
	}

	@RequestMapping(value = "update/userName", method = RequestMethod.PUT)
	public String updateUserFirstName(@RequestParam("firstName") String firstname, @RequestParam("newFName") String newFName){
		if (userController.updateUserFirstname(firstname, newFName)) {
			return newFName + " Successfully Updated";
		}
		return "Fail to update existing User :- " + newFName;
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public String deleteUserById(@PathVariable("id") int userId){
		if (userController.deleteUserById(userId)) {
			return userId +" Successfully deleted!.";
		}
		return "Fail to delete existing User of Id :- " + userId;

	}

	@RequestMapping(value = "/userById/{userId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public User getUserByUserId(@PathVariable("userId") int userId) {
		return userController.getUserByUserId(userId);
	}

	@RequestMapping(value = "/userByMob/{mobNumber}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public User getUserByMobNumber(@PathVariable("mobNumber") String mobNumber) {
		return userController.getUserByMobNumber(mobNumber);
	}
}