package com.stocktracker.mappers;

import com.stocktracker.model.UserModel;

public interface UserMapper {
	
	public UserModel loginAuth(String username, String password);

}
