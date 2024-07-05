package com.vibiya.DTO;

import jakarta.validation.constraints.NotNull;




public class UserLoginDTO {
	

	@NotNull(message = "Mobile number should not be null")
	private String mobileNumber;
	
	@NotNull(message="password should not be null")
	private String userPassword;
	
	
	public UserLoginDTO(@NotNull(message = "Mobile number should not be null") String mobileNumber,
			@NotNull(message = "password should not be null") String userPassword) {
		super();
		this.mobileNumber = mobileNumber;
		this.userPassword = userPassword;
	}
	

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	
	

}
