package com.vibiya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vibiya.exception.AdminException;
import com.vibiya.exception.UserException;
import com.vibiya.model.User;
import com.vibiya.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) throws UserException
	{
		return new ResponseEntity<User>(userService.addUser(user), HttpStatus.CREATED);
	}

	@PutMapping("/updateUser")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user,@RequestParam String key) throws UserException
	{
		return new ResponseEntity<User>(userService.updateUser(user, key), HttpStatus.OK);
	}
    
	@DeleteMapping("/deleteUser/{userId}")
   public ResponseEntity<User> deleteUser(@PathVariable Integer userId,@RequestParam String key) throws AdminException, UserException
   {
	   return new ResponseEntity<User>(userService.deleteUser(userId, key), HttpStatus.OK);
   }
	
	@GetMapping("/findUserById/{userId}")
	public ResponseEntity<User> findUserById(@PathVariable Integer userId,@RequestParam String key) throws AdminException, UserException
	{
		return new ResponseEntity<User>(userService.findUserById(userId, key), HttpStatus.OK);
	}
	
	@GetMapping("/findAllUser")
	public ResponseEntity<List<User>> findAllUser(@RequestParam String key) throws AdminException
	{
		return new ResponseEntity<List<User>>(userService.findAllUser(key), HttpStatus.OK);
	}

}
