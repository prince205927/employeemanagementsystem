package com.example.ems.service;

import com.example.ems.dto.LoginRequest;
import com.example.ems.dto.LoginResponse;
import com.example.ems.dto.RegisterRequest;
import com.example.ems.entity.User;
import com.example.ems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public String registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Error: Username is already taken!");
        }
        
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole("ROLE_USER");
        
        userRepository.save(user);
        
        return "User registered successfully!";
    }
    
    public LoginResponse loginUser(LoginRequest loginRequest) {
    	User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new BadCredentialsException("Invalid username or password"));
    	if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
    		throw new BadCredentialsException("Invalid username or password");
    	}
    	return new LoginResponse(user.getId(), user.getUsername(), user.getRole(), "Login successful");
    }
}