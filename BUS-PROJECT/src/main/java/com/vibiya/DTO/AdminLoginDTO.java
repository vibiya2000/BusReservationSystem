package com.vibiya.DTO;

import jakarta.validation.constraints.NotNull;


public class AdminLoginDTO {
	
        @NotNull(message = "Mobile number should not be null")
		private String mobileNum;
		
		@NotNull(message="password should not be null")
		private String adminPassword;

		public String getMobileNumber() {
			return mobileNum;
		}

		public void setMobileNumber(String mobileNum) {
			this.mobileNum = mobileNum;
		}

		public String getAdminPassword() {
			return adminPassword;
		}

		public void setAdminPassword(String adminPassword) {
			this.adminPassword = adminPassword;
		}

		
		
		
	}

	

