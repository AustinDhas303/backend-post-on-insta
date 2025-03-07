package com.cms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.dto.Login;
import com.cms.dto.LoginDTO;
import com.cms.dto.ResponseDTO;
import com.cms.dto.UserDTO;
import com.cms.model.Role;
import com.cms.model.User;
import com.cms.repository.RoleRepository;
import com.cms.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public String createUser(UserDTO userDTO) {
		if (userRepository.findByEmailId(userDTO.getEmailId()).isPresent()) {
	        throw new RuntimeException("Email ID already exists");
	    }

	    if (userRepository.findByPassword(userDTO.getPassword()).isPresent()) {
	        throw new RuntimeException("Incorrect Password");
	    }
	    
		roleRepository.findById(userDTO.getRoleId()).orElseThrow(() -> new RuntimeException("Role not found"));
        userRepository.save(new User(userDTO));
        return "User created successfully";
	}

	@Override
	public User validateUser(String emailId, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByEmailId(emailId)
                .filter(user -> user.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
	}

	@Override
	public User deleteUser(Long userId) {
		// TODO Auto-generated method stub
		User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user deatail not found :"+userId));
		userRepository.delete(user);
		return user;
	}

	@Override
	public ResponseDTO getAllUsers() {
		// TODO Auto-generated method stub
		ResponseDTO responseDTO=new ResponseDTO();
		List<UserDTO> userDTOs=new ArrayList<>();
		List<User> users=userRepository.findAll();

		for(User u:users) {
			UserDTO userDTO=new UserDTO();
			userDTO.setUserId(u.getUserId());
			userDTO.setFirstName(u.getFirstName());
			userDTO.setLastName(u.getLastName());
			userDTO.setPassword(u.getPassword());
			userDTO.setContactNo(u.getContactNo());
			userDTO.setStatus(u.isStatus());
			userDTO.setEmailId(u.getEmailId());
			userDTO.setAddress1(u.getAddress1());
			userDTO.setAddress2(u.getAddress2());
			userDTO.setCity(u.getCity());
			userDTO.setState(u.getState());
			userDTO.setPincode(u.getPincode());
			userDTO.setCreated_At(u.getCreated_At());
			userDTO.setUpdated_At(u.getUpdated_At());
			if (u.getRole() != null) {
	            userDTO.setRoleId(u.getRole().getRoleId());
	        }
			userDTOs.add(userDTO);
		}
		responseDTO.setUserDTO(userDTOs);
		return responseDTO;
	}

//	@Override
//	public Object loginUser(Login login) {
//		// TODO Auto-generated method stub
//		return userRepository.findByEmailId(emailId)
//                .filter(user -> user.getPassword().equals(password))
//                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
//	}
	
	@Override
	public Object loginUser(LoginDTO loginDTO) {

		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();

		User user=userRepository.findByEmailId(loginDTO.getEmailId()).orElse(null);
		if ((loginDTO.getEmailId()== null)|| (loginDTO.getEmailId().trim().isEmpty())) {
			map.put("status", "error");
			map.put("message", "Email can not be null or empty");
			return map;
		}
		if ((loginDTO.getPassword() == null)) {
			map.put("status", "error");
			map.put("message", "Password cannot be null");
			return map;
		}
		if (userRepository.countByEmailId(loginDTO.getEmailId()) == 0) {
			map.put("status", "error");
			map.put("message", "Invalid Email");
			return map;
		}
		if (userRepository.countByPassword(loginDTO.getPassword()) == 0) {
			map.put("status", "error");
			map.put("message", "Invalid Password");
			return map;
		}
		else {
			map.put("status", "Success");
			map.put("message", "Login Successfull");
//			map.put("defaultId", customer.getDefaultId());
			map.put("roleId",user.getRole().getRoleId());
			map.put("userId",user.getUserId());
			return map;
		}	

	}


		
//	@Override
//	public User createUser(UserDTO userDTO) {
//		// TODO Auto-generated method stub
//		User user = new User();
//		user.setFirstName(userDTO.getFirstName());
//		user.setLastName(userDTO.getLastName());
//		user.setPassword(userDTO.getPassword());
//		user.setEmailId(userDTO.getEmailId());
//		user.setStatus(userDTO.getStatus());
//		user.setContactNo(userDTO.getContactNo());
//		user.setAddress1(userDTO.getAddress1());
//		user.setAddress2(userDTO.getAddress2());
//		user.setCity(userDTO.getCity());
//		user.setState(userDTO.getState());
//		user.setPincode(userDTO.getPincode());
//		Role role = roleRepository.findById(userDTO.getRoleId())
//	            .orElseThrow(() -> new RuntimeException("Role not found with ID: " + userDTO.getRoleId()));
//	    user.setRole(role);
//		return userRepository.save(user);
//	}


//	@Override
//	public UserDTO fetchUserById(Integer userId) {
//		// TODO Auto-generated method stub
//		UserDTO userDTO =new UserDTO();
//		User user1 =userRepository .findById(userId).orElse(null);
//		if(user1!=null) {
//			userDTO.setFirstName(user1.getFirstName());
//			userDTO .setLastName(user1.getLastName());
//			userDTO.setPassword(user1.getPassword());
//			userDTO .setEmailId(user1 .getEmailId());
//			userDTO.setStatus(user1.getStatus());
//			userDTO .setContactNo(user1 .getContactNo());
//			userDTO .setAddress1(user1 .getAddress1());
//			userDTO .setAddress2(user1 .getAddress2());
//			userDTO.setCity(user1.getCity());
//			userDTO.setState(user1.getState());
//			userDTO.setPincode(user1.getPincode());
//		}
//		return userDTO ;
//	}
//
//	@Override
//	public User updateUser(Integer userId, UserDTO userDTO) {
//		// TODO Auto-generated method stub
//		User user2 =userRepository .findById(userId).orElse(null);
//		if(user2!=null) {
//			user2.setFirstName(userDTO.getFirstName());
//			user2.setLastName(userDTO.getLastName());
//			user2.setPassword(userDTO .getPassword());
//			user2.setEmailId(userDTO .getEmailId());
//			user2.setStatus(userDTO.getStatus());
//			user2.setContactNo(userDTO.getContactNo());
//			user2.setAddress1(userDTO.getAddress1());
//			user2.setAddress2(userDTO.getAddress2());
//			user2.setCity(userDTO.getCity());
//			user2.setState(userDTO.getState());
//			user2.setPincode(userDTO.getPincode());
//		}
//		return userRepository .save(user2);
//	}
//
//
//	@Override
//	public ResponseDTO getAllUsers() {
//		// TODO Auto-generated method stub
//

//	}
//
//	@Override
//	public User deleteUser(Integer userId) {
//		// TODO Auto-generated method stub
		
//	}
	

}
