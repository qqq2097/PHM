package kr.inhatc.spring.user.service;

import java.util.List;

import kr.inhatc.spring.user.entity.Users;

public interface UserService {

	List<Users> userList();


	Users userDetail(String id);

	void userDelete(String id);


  void saveUsers(Users user);
		
	
	}

