package com.setplex.ott.packages.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.setplex.ott.packages.model.UserPackage;
import com.setplex.ott.packages.repository.PackageRepository;

@Service
@Transactional
public class PackageService {

	@Autowired
	private PackageRepository repo;

	public UserPackage savePackage(UserPackage packages) {
		try {
			return repo.save(packages);
		} catch (Exception e) {
			System.out.println("here service error:"+e.getMessage());
			throw e;
		}
	}

	public UserPackage get(Integer id) {
		return repo.findById(id).get();
	}
	
	public UserPackage getPackageByUserId(Integer id) {
		return repo.findByUserId(id);
	}
	

}
