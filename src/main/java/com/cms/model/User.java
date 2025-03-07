package com.cms.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	
	@Column(name = "Password",length=16)
	private String password;
	
	@Column(name = "Email",length=45)
	private String emailId;
	
	@Column(name="Status")
	private boolean status;
	
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
	
	
	
public User(UserDTO userDto) {
		super();
//		this.userId = userDto.getUse;
		this.firstName = userDto.getFirstName();
		this.lastName = userDto.getLastName();
		this.password = userDto.getPassword();
		this.emailId = userDto.getEmailId();
		this.status = userDto.getStatus();
		this.contactNo = userDto.getContactNo();
		this.address1 = userDto.getAddress1();
		this.address2 = userDto.getAddress2();
		this.city = userDto.getCity();
		this.state = userDto.getState();
		this.pincode = userDto.getPincode();
		this.role = new Role(userDto.getRoleId());
	}

    

//	@PrePersist
//	protected void onCreate() {
//		this.created_At=ZonedDateTime.now();
//		this.updated_At=ZonedDateTime.now();
//	}
//	
//	@PreUpdate
//	protected void onUpdate() {
//		this.updated_At=ZonedDateTime.now();
//	}

	public User() {
	super();
	// TODO Auto-generated constructor stub
}



	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public Date getCreated_At() {
		return created_At;
	}

	public void setCreated_At(Date created_At) {
		this.created_At = created_At;
	}

	public Date getUpdated_At() {
		return updated_At;
	}

	public void setUpdated_At(Date updated_At) {
		this.updated_At = updated_At;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

		

}
