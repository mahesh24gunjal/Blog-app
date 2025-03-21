package com.codewithdurgesh.blog.blog_app_apis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.blog_app_apis.payloads.ApiResponse;
import com.codewithdurgesh.blog.blog_app_apis.payloads.UserDto;
import com.codewithdurgesh.blog.blog_app_apis.services.UserService;
@Controller
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/")
	public ResponseEntity<UserDto>createUser(@RequestBody UserDto userDto)
	{
		UserDto createUserDto=this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	
	//Put mapping for update user
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("userId")Integer uid )
	
	{
		UserDto updateduser=this.userService.updateUser(userDto, uid);
		return ResponseEntity.ok(updateduser);
	}
	
	//for deleteany User using of userId
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse>deleteUser(@PathVariable("userId") Integer uid)
	{
        this.userService.deleteUser(uid);
		return new  ResponseEntity<ApiResponse>(new ApiResponse("UserDeleted Successfully",true),HttpStatus.OK);
	}

	//fetch all users
	@GetMapping("/")
	public ResponseEntity<List<UserDto>>getAllUsers()
	{
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	//fetch only single user
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto>getSingleUser(@PathVariable("userId")Integer uid)
	{
		return ResponseEntity.ok(this.userService.getUserById(uid));
	}
}
