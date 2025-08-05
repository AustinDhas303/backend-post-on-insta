package com.cms.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.cms.dto.UserDTO;
import com.cms.dto.Login;
import com.cms.service.AuthService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Login login) {
        return authService.login(login.getEmailId(), login.getPassword());
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody UserDTO userDTO) {
        return authService.register(userDTO);
    }
}