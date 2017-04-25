package com.stocktracker.mappers;

import java.util.List;

import com.stocktracker.model.User;


public interface UserMapper {
	
	public User loginAuth(String username, String password, String role);
	public List<User> getAllUsers();
}
