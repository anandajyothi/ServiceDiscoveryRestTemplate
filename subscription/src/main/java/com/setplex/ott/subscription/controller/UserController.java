package com.setplex.ott.subscription.controller;

import com.setplex.ott.subscription.aop.LogExecutionTime;
import com.setplex.ott.subscription.config.Config;
import com.setplex.ott.subscription.exception.BadRequestException;
import com.setplex.ott.subscription.exception.InvalidDeviceTypeException;
import com.setplex.ott.subscription.exception.NotfoundException;
import com.setplex.ott.subscription.model.Event;
import com.setplex.ott.subscription.model.User;
import com.setplex.ott.subscription.model.UserResponce;
import com.setplex.ott.subscription.service.EventService;
import com.setplex.ott.subscription.service.UserService;

import java.util.*;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/subscription")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private EventService eventService;

	@Autowired
	private Config config;

	@Autowired
	RestTemplate restTemplate;
	
	// RESTful API methods for Retrieval operations
	@GetMapping("/users")
	@LogExecutionTime
	public Iterable<User> list() {
		return userService.listAll();
	}

	@GetMapping(value = "/users/{id}", produces = "application/json")
	@LogExecutionTime
	public ResponseEntity<UserResponce> get(@PathVariable Integer id) {

		try {
			User user = userService.get(id);
			String packageUrl = "http://SETPLEXPACKAGES/packages/package/user/" + id;
			System.out.println(packageUrl);
			ResponseEntity<Object> response = restTemplate.getForEntity(packageUrl, Object.class);
			UserResponce userResponce = new UserResponce(user, response.getBody());
			return new ResponseEntity<UserResponce>(userResponce, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			throw new NotfoundException(this.config.getMessage());
		}

	}
    
	@PostMapping("/users")
	@LogExecutionTime
	public void add(@RequestBody User user) {
		String userName = user.getUsername();
		User savedUser = userService.save(user);
		// event
		Event event = new Event();
		event.setId(0);
		event.setCreated_date(user.getCreatedDate());
		event.setEvent_description("User registered in to system");
		event.setEvent_type("user_registered");
		event.setUser_id(savedUser.getId());
		event.setUsername(userName);
		// insert data in to event
		this.eventService.save(event);

	}

	@PutMapping("/users/{id}")
	@LogExecutionTime
	public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer id) {
		try {

			User existUser = userService.get(id);
			if (existUser == null) {
				throw new NotfoundException("User Not found");
			}
			userService.save(user);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/users/{id}")
	@LogExecutionTime
	public void delete(@PathVariable Integer id) {
		userService.delete(id);
	}

	@GetMapping("/users/{device_type}/{username}")
	@LogExecutionTime
	public String get(@PathVariable String device_type, @PathVariable String username) {
		// try {
		if (device_type == null || username == null) {
			throw new BadRequestException("Invalid request!");
		}
		User user = null;

		user = userService.findbByUsername(username);
		if (user == null) {
			throw new NotfoundException("Invalid userId!");
		}

		Event event = new Event();
		event.setUser_id(user.getId());
		event.setUsername(username);
		if (user.getDevice_type().equalsIgnoreCase(device_type)) {
			// update user subscription status
			this.userService.update(user);
			// insert data in event table
			event.setEvent_description("User logged in to system");
			event.setEvent_type("user_login");
			this.eventService.save(event);
			return "Welcome again";
		} else {
			event.setEvent_description("Your device is not eligible to view the content ");
			event.setEvent_type("Invalid device");
			this.eventService.save(event);
			throw new InvalidDeviceTypeException("Your device is not eligible to view the content!");
		}

	}

}
