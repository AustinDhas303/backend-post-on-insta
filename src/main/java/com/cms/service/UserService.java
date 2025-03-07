package com.cms.service;

import com.cms.dto.Login;
import com.cms.dto.LoginDTO;
import com.cms.dto.ResponseDTO;
import com.cms.dto.UserDTO;
import com.cms.model.User;

public interface UserService {

	String createUser(UserDTO userDTO);

	User validateUser(String emailId, String password);

	User deleteUser(Long userId);

	ResponseDTO getAllUsers();

//	User loginUser(String emailId, String password);

	Object loginUser(LoginDTO loginDTO);
}
