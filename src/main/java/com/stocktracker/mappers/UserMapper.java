package com.stocktracker.mappers;

import java.util.List;

import com.stocktracker.model.User;


public interface UserMapper {
	
	public int loginAuth(String emailAddress, String password, String role);
	public List<User> getAllUsers();
}
