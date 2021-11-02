package com.example.app.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.app.dto.input.CreateUserDTO;
import com.example.app.dto.input.UpdateUserDTO;
import com.example.app.dto.output.UserDTO;
import com.example.app.model.address.Address;
import com.example.app.model.user.User;
import com.example.app.service.AddressService;
import com.example.app.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	@Autowired
	private final UserService userService;
	
	@Autowired
	private final AddressService addresService;

	@GetMapping()
	 public List<User> findall() {
	  return userService.findAll();
	 }
	
	@GetMapping("/{id}")
	public UserDTO get(@PathVariable String id){
		Address address = addresService.get(id);
		Optional<User> user = userService.get(id);
		
		if (user.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found.");
		}

		User users = user.get();

		return new UserDTO(users, address);
	}

	@PostMapping()
	public UserDTO create(@RequestBody CreateUserDTO input) {
		User user = userService.create(input);
		Address address = addresService.create(input, user.getId());

		return new UserDTO(user, address);
	}

	@PutMapping("/{id}")
	public UserDTO update(@PathVariable String id, @RequestBody UpdateUserDTO input) {
		User user = userService.update(id, input);
		Address address = addresService.update(id, input);

		return new UserDTO(user, address);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		userService.remove(id);
		addresService.remove(id);
	}

}
