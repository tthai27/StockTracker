package com.stocktracker.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stocktracker.mappers.UserMapper;
import com.stocktracker.model.User;
import com.stocktracker.service.UserService;
import com.stocktracker.util.MyBatisUtil;

@Service
public class UserServiceImpl implements UserService {

//	@Autowired
//	UserMapper userMapper;

	@Override
	public int getUserInfo(User user) {
		// TODO Auto-generated method stub
		//userMapper.loginAuth(username, password);
		return 0;
	}

	@Override
	public boolean loginAuthService(String username, String password, String role) {
		// TODO Auto-generated method stub
		//userMapper.loginAuth(username, password, role);
		return false;
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
		
		return new ArrayList<User>();//userMapper.getAllUsers();
	}

	@Override
	public int getUsersCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	

	


}

