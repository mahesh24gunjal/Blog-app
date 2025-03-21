package com.codewithdurgesh.blog.blog_app_apis.services.imple;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.blog_app_apis.entities.User;
import com.codewithdurgesh.blog.blog_app_apis.exception.ResourceNotFound;
import com.codewithdurgesh.blog.blog_app_apis.payloads.UserDto;
import com.codewithdurgesh.blog.blog_app_apis.repository.UserRepo;
import com.codewithdurgesh.blog.blog_app_apis.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User saveUser=this.userRepo.save(user);
		return this.userToDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFound("User","Id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser=this.userRepo.save(user);
		UserDto userDto1=this.userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFound("User", "Id",userId));
		return this.userToDto(user);
		
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users=this.userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
	    return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFound("User", "Id", userId));
	    this.userRepo.delete(user);
	}
	
	private User dtoToUser(UserDto userDto)
	{
		 User user=new User();
		 user.setId(userDto.getId());
		 user.setName(userDto.getName());
		 user.setEmail(userDto.getEmail());
		 user.setAbout(userDto.getAbout());
		 user.setPassword(userDto.getPassword());
		 return user;
	}
	
	private UserDto userToDto(User user)
	{
		 UserDto userDto=new UserDto();
		 userDto.setId(user.getId());
		 userDto.setName(user.getName());
		 userDto.setEmail(user.getEmail());
		 userDto.setAbout(user.getAbout());
		 userDto.setPassword(user.getPassword());
		 return userDto;
	}

}
