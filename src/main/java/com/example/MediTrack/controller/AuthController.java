package com.example.MediTrack.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.MediTrack.JwtTokenProvider;
import com.example.MediTrack.dto.JwtResponse;
import com.example.MediTrack.dto.LoginRequest;
import com.example.MediTrack.dto.RegisterRequest;
import com.example.MediTrack.model.Role;
import com.example.MediTrack.model.User;
import com.example.MediTrack.repository.RoleRepository;
import com.example.MediTrack.repository.UserRepository;

import java.util.Set;
 
@RestController
@CrossOrigin(origins = "http://localhost:4200",allowCredentials = "true")
@RequestMapping("/api/v1/auth")
public class AuthController {
	
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
 
    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenProvider tokenProvider,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          BCryptPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
 
    // LOGIN ENDPOINT
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Generate JWT
        String jwt = tokenProvider.generateToken(authentication); 
        // Return JWT in the response
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
 
    // REGISTER ENDPOINT
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }
 
        // Create new user's account
        User user = new User();
        user.setFirstname(registerRequest.getFirstname());
        user.setLastname(registerRequest.getLastname());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEnabled(true);
 
        // Assign roles (default role is 'user')
        Role userRole = roleRepository.findByName("user")
                .orElseThrow(() -> new RuntimeException("Error: Role not found."));
        user.setRoles(Set.of(userRole));
 
        userRepository.save(user);
 
        return ResponseEntity.ok("User registered successfully!");
    }
}