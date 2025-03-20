package com.codewithdurgesh.blog.blog_app_apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewithdurgesh.blog.blog_app_apis.entities.User;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
