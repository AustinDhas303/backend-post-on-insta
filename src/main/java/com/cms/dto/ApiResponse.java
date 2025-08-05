package com.cms.dto;

import com.cms.model.User;

import lombok.Data;

@Data
public class ApiResponse {
	
	    private boolean success;
	    private String message;
		public ApiResponse(boolean success, String message) {
			super();
			this.success = success;
			this.message = message;
		}
		public ApiResponse(boolean b, String string, User user) {
			// TODO Auto-generated constructor stub
		}    
}
