package com.cms.controller;


import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cms.dto.ResponseDTO;
import com.cms.dto.UserDTO;
import com.cms.dto.UserSearchDTO;
import com.cms.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@DeleteMapping("/deleteuser/{userId}")
    public Map<String, String> deleteUser(@PathVariable Long userId){
    	return userService.deleteUser(userId);
    }

	@GetMapping("/fetchallusers")
	public ResponseDTO getAllUsers(@RequestParam int page, @RequestParam int size, @RequestParam String userName){
		return userService.getAllUsers(page, size, userName);
	}
	
	@PutMapping("/updateUser")
	public Map<String, String> updateUser(@RequestBody UserDTO userDTO){
		return userService.updateUser(userDTO);
	}
	
	@PostMapping("/logout")
	public Object logout() {
		return userService.logout();
	}
	
}
