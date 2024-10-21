/*package com.example.MediTrack.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.MediTrack.model.Role;
import com.example.MediTrack.model.User;
import com.example.MediTrack.repository.RoleRepository;
import com.example.MediTrack.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	

	@Override
	public void saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setEnabled(true);
		Role userRole= roleRepository.findByName("user");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);

	}

	@Override
	public boolean isUserAlreadyPresent(User user) {
		return false;
	}

	@Override
	public User getUser(String username) {
		return userRepository.getUserByUsername(username);
	}

}
*/