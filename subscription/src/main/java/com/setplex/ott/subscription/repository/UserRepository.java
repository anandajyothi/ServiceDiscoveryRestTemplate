package com.setplex.ott.subscription.repository;


import org.springframework.data.repository.CrudRepository;

import com.setplex.ott.subscription.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByUsername(String username);
   
}