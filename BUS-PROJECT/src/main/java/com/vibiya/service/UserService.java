package com.vibiya.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vibiya.exception.AdminException;
import com.vibiya.exception.UserException;
import com.vibiya.model.AdminLoginSession;
import com.vibiya.model.User;
import com.vibiya.model.UserLoginSession;
import com.vibiya.repository.AdminSessionDAO;
import com.vibiya.repository.UserDAO;
import com.vibiya.repository.UserSessionDAO;

@Service 
public class UserService {
	
	
	@Autowired
	UserSessionDAO userSessionDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	AdminSessionDAO adminSessionDAO;
	
	
	public User addUser(User user) throws UserException
	{
		User existingUser = userDAO.findByMobileNumber(user.getMobileNumber()); 
		if(existingUser!=null)
		{
			throw new UserException("User already registered with this mobile number");
		}
		
		return userDAO.save(user);
	}
	
	public User updateUser(User user,String key) throws UserException 
	{
		UserLoginSession LoggedInUser = userSessionDAO.findByUuid(key);
		if(LoggedInUser==null)
		{
			throw new UserException("Please provide valid key to update");
		}
		
		if(LoggedInUser.getUserId().equals(user.getUserId()))
		{
			return userDAO.save(user);
		}
		else throw new UserException("Invalid User Deatils,LogIn first");
	}
	
	public User deleteUser(Integer userId,String key) throws AdminException, UserException
	{
		AdminLoginSession loggedInAdmin = adminSessionDAO.findByUuid(key);
		if(loggedInAdmin==null)
		{
			throw new AdminException("Please provide valid key");
		}
		User user =userDAO.findById(userId).orElseThrow(()->new UserException("No such user exists"));
		userDAO.delete(user);
		return user;
	}
	
	public User findUserById(Integer userId,String key) throws AdminException, UserException
	{
		AdminLoginSession loggedInAdmin = adminSessionDAO.findByUuid(key);
		if(loggedInAdmin==null)
		{
			throw new AdminException("Please provide valid key");
		}
		User user =userDAO.findById(userId).orElseThrow(()->new UserException("No such user exists"));
		return user;
	}
	
	public List<User> findAllUser(String key) throws AdminException
	{
		AdminLoginSession loggedInAdmin = adminSessionDAO.findByUuid(key);
		if(loggedInAdmin==null)
		{
			throw new AdminException("Please provide valid key");
		}
		 List<User> userList= userDAO.findAll();
		 return userList;
	}

}
