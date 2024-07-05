package com.vibiya.model;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "mobileNum"),
		                     @UniqueConstraint(columnNames = "adminPassword"),
		                     @UniqueConstraint(columnNames = "adminId")})
public class Admin {

	@Id
	@Column(unique = true) 
	private Integer adminId;
	@NotBlank(message = "Name connot be blank!")
	private String name;
	@Column(unique = true)
	@Size(min = 10, max = 10)
	private String mobileNum;
	@Column(unique = true)
	@NotBlank(message= "Password cannot be blank!")
	@Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}", message = "Password must be 8-15 characters in length and can include alphanumerics and special characters")
	private String adminPassword;
	@Email(message = "xyz@gmail.com")
	private String email;
	
	
	public Admin()
	{
		
	}
	
	public Admin(Integer adminId, @NotBlank(message = "Name connot be blank!") String name,
			@Size(min = 10, max = 10) String mobileNum,
			@NotBlank(message = "Password cannot be blank!") @Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}", message = "Password must be 8-15 characters in length and can include alphanumerics and special characters") String adminPassword,
			@Email String email) {
		super();
		this.adminId = adminId;
		this.name = name;
		this.mobileNum = mobileNum;
		this.adminPassword = adminPassword;
		this.email = email;
	}
	
	
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNumber) {
		this.mobileNum = mobileNumber;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
	


}
