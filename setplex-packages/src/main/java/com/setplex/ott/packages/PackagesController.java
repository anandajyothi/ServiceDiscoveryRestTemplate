package com.setplex.ott.packages;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.setplex.ott.packages.model.UserPackage;
import com.setplex.ott.packages.service.PackageService;

@RestController
@RequestMapping("/packages")
public class PackagesController {

	@Autowired
	private PackageService service;

	@GetMapping("/")
	public String hello() {
		return "Hello world";

	}
	
	@GetMapping("/package/user/{userId}")
	public ResponseEntity<UserPackage> getPackageById(@PathVariable Integer userId) {
		try {
			System.out.println("data"+userId);
			UserPackage userPackage = service.getPackageByUserId(userId);
			return new ResponseEntity<UserPackage>(userPackage, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping("/package")
	public ResponseEntity<UserPackage> add(@RequestBody UserPackage userPackage) {
		try {
		UserPackage savedUserPackage = service.savePackage(userPackage);
		return new ResponseEntity<UserPackage>(savedUserPackage, HttpStatus.OK);
		}
		 catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

	}
}
