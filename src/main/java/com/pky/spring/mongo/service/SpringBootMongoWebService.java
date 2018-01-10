package com.pky.spring.mongo.service;

import com.pky.spring.mongo.controller.UserController;
import com.pky.spring.mongo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@RestController @RequestMapping(value = "/") public class SpringBootMongoWebService {

	@Autowired private UserController userController;

	/**
	 * Store/add a new user into DB
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public String addUserToMongoDb(@RequestBody User user) {
		if (userController.addUserToMongoDb(user)) {
			return user.toString() + " Successfully added";
		}
		return "Fail to add/register User :- " + user.toString();
	}

	/**
	 * update whole user by new user data
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public String updateUserById(@RequestBody User user) {
		if (userController.updateUser(user)) {
			return user.toString() + " Successfully Updated";
		}
		return "Fail to update existing User :- " + user.toString();
	}

	/**
	 * will update firstName of existing user by new firstname
	 * @param firstname
	 * @param newFName
	 * @return
	 */
	@RequestMapping(value = "update/userName", method = RequestMethod.PUT)
	public String updateUserFirstName(@RequestParam("firstName") String firstname, @RequestParam("newFName") String newFName){
		if (userController.updateUserFirstname(firstname, newFName)) {
			return newFName + " Successfully Updated";
		}
		return "Fail to update existing User :- " + newFName;
	}

	/**
	 * this will delete a user on the basis of userId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public String deleteUserById(@PathVariable("id") int userId){
		if (userController.deleteUserById(userId)) {
			return userId +" Successfully deleted!.";
		}
		return "Fail to delete existing User of Id :- " + userId;

	}

	/**
	 * will fetch user on the basis of userId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/userById/{userId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public User getUserByUserId(@PathVariable("userId") int userId) {
		return userController.getUserByUserId(userId);
	}

	/**
	 * will fetch user on the basis of mobile number
	 * @param mobNumber
	 * @return
	 */
	@RequestMapping(value = "/userByMob/{mobNumber}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE })
	public User getUserByMobNumber(@PathVariable("mobNumber") String mobNumber) {
		return userController.getUserByMobNumber(mobNumber);
	}

	/**
	 * This will fetch multiple user having same firstname
	 * @param firstName
	 * @return
	 */
	@RequestMapping(value = "/allUser/{firstName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<User> getAllUserHavingSameFirstName(@PathVariable("firstName") String firstName){
		return userController.getAllUserOfName(firstName);
	}

}