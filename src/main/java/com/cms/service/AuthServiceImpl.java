package com.cms.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cms.config.JwtUtil;
import com.cms.dto.UserDTO;
import com.cms.model.Role;
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
	public Map<String, String> register(UserDTO userDTO) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		String email = userDTO.getEmailId();
		String mobile = userDTO.getContactNo();
		
		int emailCount = userRepository.countByEmailId(email);
		int mobileCount = userRepository.countByMobile(mobile);
	    
		if (emailCount > 0) {
			map.put("status", "error");
			map.put("message", "Email Id is Already Exist");
			return map;
		}
		if (mobileCount > 0) {
			map.put("status", "error");
			map.put("message", "Mobile Number is Already Exist");
			return map;
		}
		
		Integer roleId = userDTO.getRole().getRoleId();
		Role role = roleRepository.findById(roleId)
		               .orElseThrow(() -> new RuntimeException("Role not found"));
		
		String password = new BCryptPasswordEncoder().encode(userDTO.getPassword());
		User user = new User();
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setAddress1(userDTO.getAddress1());
		user.setAddress2(userDTO.getAddress2());
		user.setCity(userDTO.getCity());
		user.setContactNo(userDTO.getContactNo());
		user.setCreated_At(userDTO.getCreated_At());
		user.setEmailId(userDTO.getEmailId());
		user.setPassword(password);
		user.setPincode(userDTO.getPincode());
		user.setRole(role);
		user.setState(userDTO.getState());
		user.setStatus(userDTO.getStatus());
		user.setUpdated_At(userDTO.getUpdated_At());
		userRepository.save(user);
		map.put("status", "success");
		map.put("message", "User created successfully");
        return map;
	}

}
