package com.stocktracker.service;

import java.util.List;

import com.stocktracker.model.User;

public interface UserService {
	public int getUserInfo(User user);
	
	public List<User> getAllUsers();
	public boolean loginAuthService(String username, String password, String role);
}
