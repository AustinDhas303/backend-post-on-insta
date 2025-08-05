package com.cms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="role_master")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer roleId;
	private String roleName;
	
//	public Role(Integer roleId) {
//		this.roleId = roleId;
//	}
	
//	public Role() {
//		super();
		// TODO Auto-generated constructor stub
//	}

//	public int getRoleId() {
		// TODO Auto-generated method stub
//		return 0;
//	}
	
}
