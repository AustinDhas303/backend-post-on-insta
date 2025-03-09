package com.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.dto.ApiResponse;
import com.cms.dto.JwtResponse;
import com.cms.dto.Login;
import com.cms.dto.LoginDTO;
import com.cms.dto.ResponseDTO;
import com.cms.dto.UserDTO;
import com.cms.model.User;
import com.cms.service.JwtUtility;
import com.cms.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@PreAuthorize("hasAuthority('Admin')")
@RequestMapping("/admin")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@DeleteMapping("/deleteuser/{userId}")
    public ResponseEntity<String>deleteUser(@PathVariable Long userId){
    	User user=userService.deleteUser(userId);
    	return new ResponseEntity<>("Delete Sucess",HttpStatus.OK);
    }

	@GetMapping("/fetchallusers")
	public ResponseEntity<ResponseDTO> getAllUsers(){
		ResponseDTO responseDTO=userService.getAllUsers();
		return new ResponseEntity<>(responseDTO,HttpStatus.OK);
	}
	
//	@PostMapping("/userlogin")
//	public ResponseEntity<?> loginUser(@RequestBody Login login) {
//	    try {
//	        // Call the service layer to authenticate the user
//	        User user = userService.loginUser(login.getEmailId(), login.getPassword());
//	        
//	        return ResponseEntity.ok("Login successful");
//	    } catch (RuntimeException e) {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + e.getMessage());
//	    }
//	}

	
//	@GetMapping("fetchUser/{user_Id}")
//	public ResponseEntity<UserDTO> getUserById(@PathVariable Integer user_Id){
//		UserDTO userDTO=userService .fetchUserById(user_Id);
//		return new ResponseEntity<>(userDTO ,HttpStatus.OK); 
//	}
//
//	@PutMapping ("updateUser/{user_Id}")
//	public ResponseEntity<User> updateUser(@PathVariable Integer user_Id,@RequestBody UserDTO userDTO ){
//		User user =userService .updateUser(user_Id ,userDTO );
//		return new ResponseEntity<>(user,HttpStatus.OK);
//	}
//	
	
}
