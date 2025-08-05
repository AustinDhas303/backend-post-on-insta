package com.cms.service;

import java.util.Map;

import com.cms.dto.ResponseDTO;
import com.cms.dto.UserDTO;
import com.cms.dto.UserSearchDTO;
import com.cms.model.User;

public interface UserService {

//	String createUser(UserDTO userDTO);

	User validateUser(String emailId, String password);

	Map<String, String> deleteUser(Long userId);

	ResponseDTO getAllUsers(int page, int size, String userName);

//	User loginUser(String emailId, String password);

//	Object loginUser(LoginDTO loginDTO);

	Object logout();

	Map<String, String> updateUser(UserDTO userDTO);
}
