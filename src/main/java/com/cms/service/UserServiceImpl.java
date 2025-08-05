package com.cms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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
	public User validateUser(String emailId, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByEmailId(emailId)
                .filter(user -> user.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
	}

	@Override
	public Map<String, String> deleteUser(Long userId) {
		// TODO Auto-generated method stub
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String emailId = userDetails.getUsername();
		User u = userRepository.getEmail(emailId);
		Map<String, String> map = new HashMap<String, String>();
		if(u.getRole().getRoleId() == 1) {
			User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user deatail not found :"+userId));
			userRepository.delete(user);
			map.put("message", "User deleted successfully");
		}else {
			map.put("error", "Only admin's allowed to delete user");
			return map;
		}
		return map;
	}

	@Override
	public ResponseDTO getAllUsers(int page, int size, String userName) {
		// TODO Auto-generated method stub
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String emailId = userDetails.getUsername();
		User user = userRepository.getEmail(emailId);
		ResponseDTO responseDTO=new ResponseDTO();
		if(user.getRole().getRoleId() == 1) {
			int pag = page;
			int siz = size;
			Pageable pageable = PageRequest.of(pag, siz);
			String user1 = userName;
			List<UserDTO> userDTOs=new ArrayList<>();
			List<User> users=userRepository.getAllUsers(pageable, user1);

			for(User u:users) {
				UserDTO userDTO=new UserDTO();
				userDTO.setUserId(u.getUserId());
				userDTO.setFirstName(u.getFirstName());
				userDTO.setLastName(u.getLastName());
				userDTO.setContactNo(u.getContactNo());
				userDTO.setStatus(u.getStatus());
				userDTO.setEmailId(u.getEmailId());
				userDTO.setAddress1(u.getAddress1());
				userDTO.setAddress2(u.getAddress2());
				userDTO.setCity(u.getCity());
				userDTO.setState(u.getState());
				userDTO.setPincode(u.getPincode());
				userDTO.setCreated_At(u.getCreated_At());
				userDTO.setUpdated_At(u.getUpdated_At());
				
				Integer roleId = u.getRole().getRoleId();
				Role role = roleRepository.findById(roleId)
				               .orElseThrow(() -> new RuntimeException("Role not found"));
				if (role != null) {
		            userDTO.setRole(role);
		        }
				userDTOs.add(userDTO);
			}
			responseDTO.setUserDTO(userDTOs);
		}
		
		return responseDTO;
	}

	@Override
	public Object logout() {
		// TODO Auto-generated method stub
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String, Object> map = new HashMap<String, Object>();
		String emailId = userDetails.getUsername();
		User user = userRepository.getEmail(emailId);
		map.put("status", "success");
		map.put("message", "User logged out successfully");
		return map;
	}

	@Override
	public Map<String, String> updateUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		Map<String, String> response = new HashMap<>();

	    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String emailId = userDetails.getUsername();
	    User loggedInUser = userRepository.getEmail(emailId);

	    if (loggedInUser == null) {
	        response.put("status", "fail");
	        response.put("message", "User not found in system.");
	        return response;
	    }

	    Long requestedUserId = userDTO.getUserId();

	    if (!loggedInUser.getUserId().equals(requestedUserId)) {
	        response.put("status", "unauthorized");
	        response.put("message", "You can only update your own profile.");
	        return response;
	    }

	    loggedInUser.setFirstName(userDTO.getFirstName());
	    loggedInUser.setLastName(userDTO.getLastName());
	    loggedInUser.setEmailId(userDTO.getEmailId());
	    loggedInUser.setContactNo(userDTO.getContactNo());
	    loggedInUser.setAddress1(userDTO.getAddress1());
	    loggedInUser.setAddress2(userDTO.getAddress2());
	    loggedInUser.setCity(userDTO.getCity());
	    loggedInUser.setState(userDTO.getState());
	    loggedInUser.setPincode(userDTO.getPincode());

	    userRepository.save(loggedInUser);

	    response.put("status", "success");
	    response.put("message", "Profile updated successfully.");
	    return response;
	}

}
