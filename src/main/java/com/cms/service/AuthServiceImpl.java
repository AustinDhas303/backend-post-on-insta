package com.cms.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cms.config.JwtUtil;
import com.cms.dto.UserDTO;
import com.cms.model.User;
import com.cms.repository.RoleRepository;
import com.cms.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService{

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthServiceImpl(UserRepository userRepository, JwtUtil jwtUtil, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.roleRepository=roleRepository;
    }
    
	@Override
	public Map<String, Object> login(String emailId, String password) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmailId(emailId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate JWT with email and role
        String token = jwtUtil.generateToken(user.getEmailId(), user.getRole().getRoleName());

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("role", user.getRole().getRoleName()); // Return role
        response.put("status", "Login success");
//		response.put("roleId",user.getRole().getRoleId());
		response.put("userId",user.getUserId());

        return response;
	}

	@Override
	public String register(UserDTO userDTO) {
		// TODO Auto-generated method stub
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

}
