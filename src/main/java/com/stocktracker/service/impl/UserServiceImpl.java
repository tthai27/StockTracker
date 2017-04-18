package com.stocktracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stocktracker.mappers.UserMapper;
import com.stocktracker.model.UserModel;
import com.stocktracker.service.UserService;

@Service
public class UserServiceImpl implements UserService {

//	@Autowired
//	UserMapper userMapper;

	@Override
	public int getUserInfo(UserModel userModel) {
		// TODO Auto-generated method stub
		//userMapper.loginAuth(username, password);
		return 0;
	}
	

	


}

