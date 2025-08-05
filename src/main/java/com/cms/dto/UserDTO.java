package com.cms.dto;

import java.util.Date;

import com.cms.model.Role;

import lombok.Data;

@Data
public class UserDTO {
	
	private Long userId;
	private String firstName;
	private String lastName;
	private String password;
	private String emailId;
	private int status;
	private String contactNo;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private Integer pincode;
	private Date created_At;
	private Date updated_At;
	private Role role;
	public void setRoleId(Integer roleId2) {
		// TODO Auto-generated method stub
		
	}
	
}
