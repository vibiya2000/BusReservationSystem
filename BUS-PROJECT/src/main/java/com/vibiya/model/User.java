package com.vibiya.model;

import java.util.ArrayList;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "userId"),
		                    @UniqueConstraint(columnNames = "userPassword"),
		                    @UniqueConstraint(columnNames = "mobileNumber")})
public class User {
	
	
	@Id
	@Column(unique = true)
	private Integer userId;
	@NotBlank(message = "Name connot be blank!")
	private String name;
	@Column(unique = true)
	@NotBlank(message= "Password cannot be blank!")
	@Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}", message = "Password must be 8-15 characters in length and can include alphanumerics and special characters")
	private String userPassword;
	@Column(unique = true)
	@Size(min = 10, max = 10)
	private String mobileNumber;
	@Email(message="xyz@gmail.com")
	private String email;
	
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	private List<Reservation> reservations = new ArrayList<>();
	
	
	
	public User()
	{
		
	}

	public User(Integer userId, @NotBlank(message = "Name connot be blank!") String name,
			@NotBlank(message = "Password cannot be blank!") @Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}", message = "Password must be 8-15 characters in length and can include alphanumerics and special characters") String userPassword,
			@Size(min = 10, max = 10) String mobileNumber, @Email String email, List<Reservation> reservations) {
		super();
		this.userId = userId;
		this.name = name;
		this.userPassword = userPassword;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.reservations = reservations;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<Reservation> getReservations() {
		return reservations;
	}


	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	
	
	
	
	
	


}
