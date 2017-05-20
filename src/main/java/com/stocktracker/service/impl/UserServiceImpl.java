package com.stocktracker.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stocktracker.mappers.UserMapper;
import com.stocktracker.model.User;
import com.stocktracker.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	Logger logger =  Logger.getLogger(UserServiceImpl.class);  
	@Autowired
	UserMapper userMapper;

	@Override
	public int getUserInfo(User user) {
		// TODO Auto-generated method stub
		//userMapper.loginAuth(username, password);
		return 0;
	}

	@Override
	public boolean loginAuthService(String emailAddress, String password, String role) {
		// TODO Auto-generated method stub
		//int result = userMapper.loginAuth(emailAddress, password, role);
		//System.out.println("loginAuth =" + result);
		System.out.println("count="+getAllUsers().size());
		int result = 1;
		if(result == 0) return false;
		
		return true;
	}

	@Override
	public List<User> getAllUsers() {
//		  SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
//		  try{
//		  UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//		  return userMapper.getAllUsers();
//		  }finally{
//		   sqlSession.close();
//		  }
		
		//return new ArrayList<User>();
		return userMapper.getAllUsers();
	}

	@Override
	public int getUsersCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	

	


}

