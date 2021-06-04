package com.setplex.ott.packages.repository;
import org.springframework.data.repository.CrudRepository;
import com.setplex.ott.packages.model.UserPackage;
public interface PackageRepository extends CrudRepository<UserPackage, Integer> {

	public UserPackage findByUserId(Integer user_id);
}