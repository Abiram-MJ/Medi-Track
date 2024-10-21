package com.example.MediTrack.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.MediTrack.model.User;
import com.example.MediTrack.repository.UserRepository;

import java.util.Optional;
 
@Service
public class CustomUserDetailsService implements UserDetailsService {
 
    private final UserRepository userRepository;
 
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
 
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return UserPrincipal.create(user);
    }
 
    // This method is useful for JWT or token-based systems
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
        return UserPrincipal.create(user);
    }
}
