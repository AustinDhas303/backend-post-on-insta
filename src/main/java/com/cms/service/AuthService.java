package com.cms.service;

import java.util.Map;

import com.cms.dto.UserDTO;

public interface AuthService {

	Map<String, Object> login(String emailId, String password);

	String register(UserDTO userDTO);

}
