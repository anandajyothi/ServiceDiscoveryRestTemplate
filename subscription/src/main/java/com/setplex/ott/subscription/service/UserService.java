package com.setplex.ott.subscription.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.setplex.ott.subscription.exception.InternalServerErrorException;
import com.setplex.ott.subscription.model.User;
import com.setplex.ott.subscription.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repo;

	public Iterable<User> listAll() {
		return repo.findAll();
	}

	public User findbByUsername(String username) {
		return repo.findByUsername(username);
	}

	public User save(User user)  {
		try {
		return repo.save(user);
		}
		catch(Exception e) {
			throw new InternalServerErrorException(e.getMessage(),e.getCause());
		}
	}

	public User update(User user) {
		User updateUser = new User();
		updateUser.setId(user.getId());
		updateUser.setSubcription(1);
		updateUser.setDevice_type(user.getDevice_type());
		updateUser.setUsername(user.getUsername());
		updateUser.setCreatedDate(user.getCreatedDate());
		updateUser.setUpdated_date(user.getUpdated_date());
		return repo.save(user);
	}

	public User get(Integer id) {
		return repo.findById(id).get();
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}
}
