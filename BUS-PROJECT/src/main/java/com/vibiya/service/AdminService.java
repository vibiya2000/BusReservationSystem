package com.vibiya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vibiya.exception.AdminException;
import com.vibiya.model.Admin;
import com.vibiya.model.AdminLoginSession;
import com.vibiya.repository.AdminDAO;
import com.vibiya.repository.AdminSessionDAO;

@Service
public class AdminService {
	
	@Autowired
	private AdminSessionDAO adminSessionDao;
	
	@Autowired
	private AdminDAO adminDao;
	
	
	
	public Admin createAdmin(Admin admin) throws AdminException
	{
		Admin existingAdmin = adminDao.findByMobileNum(admin.getMobileNum());
		
		if(existingAdmin!=null)
			
		{
			throw new AdminException("Admin already exists");
		}
		return adminDao.save(admin);
	}
	
	public Admin updateAdmin(Admin admin,String key) throws AdminException
	{
		AdminLoginSession validAdminSessionOpt=adminSessionDao.findByUuid(key);
		
		if(validAdminSessionOpt==null)
		{
			throw new AdminException("Please enter valid key to update Admin");
		}
		
		if(admin.getAdminId().equals(validAdminSessionOpt.getAdminId()))
		{
		return adminDao.save(admin);
	    
		}
		
		throw new AdminException("Invalid admin details");
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
