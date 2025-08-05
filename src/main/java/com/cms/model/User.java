package com.cms.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cms.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UserId")
	private Long userId;
	
	@Column(name="FirstName",length=30) 
	private String firstName;
	
	@Column(name="LastName",length=30) 
	private String lastName;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "Email",length=45)
	private String emailId;
	
	@Column(name="Status")
	private int status;
	
	@Column(name = "ContactNo",length=10)
	private String contactNo;
	
	@Column(name = "Address1",length=45)
	private String address1;
	
	@Column(name = "Address2",length=45)
	private String address2;
	
	@Column(name = "City",length=45)
	private String city;
	
	@Column(name = "State",length=45)
	private String state;
	
	@Column(name = "Pincode",length=6)
	private Integer pincode;
	
	@Column(name = "CreatedAt",updatable =false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_At;
	
	@Column(name = "UpdatedAt")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_At;
	
	@ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
	
	
	
//public User(UserDTO userDto) {
//		super();
//		this.userId = userDto.getUse;
//		this.firstName = userDto.getFirstName();
//		this.lastName = userDto.getLastName();
//		this.password = new BCryptPasswordEncoder().encode(userDto.getPassword());
//		this.emailId = userDto.getEmailId();
//		this.status = userDto.getStatus();
//		this.contactNo = userDto.getContactNo();
//		this.address1 = userDto.getAddress1();
//		this.address2 = userDto.getAddress2();
//		this.city = userDto.getCity();
//		this.state = userDto.getState();
//		this.pincode = userDto.getPincode();
//		this.role = new Role(userDto.getRoleId());
//	}

//public User() {
//	super();
	// TODO Auto-generated constructor stub
//}

}
