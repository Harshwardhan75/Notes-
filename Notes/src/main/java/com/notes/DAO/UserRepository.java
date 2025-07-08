package com.notes.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notes.Model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	public User findByEmail(String email);
	
}
