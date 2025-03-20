package com.codewithdurgesh.blog.blog_app_apis.services;

import java.util.List;

import com.codewithdurgesh.blog.blog_app_apis.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userid);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);
	
	
	

}
