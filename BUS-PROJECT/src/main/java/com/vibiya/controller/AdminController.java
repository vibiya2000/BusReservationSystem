package com.vibiya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vibiya.exception.AdminException;
import com.vibiya.model.Admin;
import com.vibiya.service.AdminService;

import jakarta.validation.Valid;

@RestController
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@PostMapping("/admin")
	public ResponseEntity<Admin> createAdmin(@Valid @RequestBody Admin admin) throws AdminException
	{
		return new ResponseEntity<Admin>(adminService.createAdmin(admin),HttpStatus.CREATED);
	}
	
	@PutMapping("/updateAdmin")
   public ResponseEntity<Admin> updateAdmin(@Valid @RequestBody Admin admin,@RequestParam String key ) throws AdminException
	{
		return new ResponseEntity<Admin>(adminService.updateAdmin(admin, key), HttpStatus.OK);

	}
}

