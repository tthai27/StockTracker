package com.stocktracker.mappers;

import java.util.List;

import com.stocktracker.model.User;


public interface UserMapper {
	
	public User loginAuth(String username, String password);
	public List<User> getAllUsers();
}
