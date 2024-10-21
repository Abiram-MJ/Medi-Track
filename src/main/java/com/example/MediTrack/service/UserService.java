package com.example.MediTrack.service;

import com.example.MediTrack.model.User;

public interface UserService {
	
	public void saveUser(User user);
	public boolean isUserAlreadyPresent(User user);
	public User getUser(String username);
	
}