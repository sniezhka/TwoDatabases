package com.example.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.app.dto.input.CreateUserDTO;
import com.example.app.dto.input.UpdateUserDTO;
import com.example.app.model.user.User;
import com.example.app.model.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserService {
	@Autowired
	private final UserRepository userRepository;

	public List<User> findAll() { 
		return (List<User>) userRepository.findAll();
	 }

	public Optional<User> get(String id) {
		return userRepository.findById(id);
	}

	public User create(CreateUserDTO dto) {
		return userRepository.save(new User(dto.getFirstName(), dto.getMiddleName(), dto.getLastName()));
	}

	public User update(String id, UpdateUserDTO dto) {
		User user = new User(id, dto.getFirstName(), dto.getMiddleName(), dto.getLastName());
		return userRepository.save(user);
	}

	public void remove(String id) {
		userRepository.deleteById(id);
	}

}
