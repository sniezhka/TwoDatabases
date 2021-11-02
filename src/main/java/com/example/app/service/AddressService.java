package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.app.dto.input.CreateUserDTO;
import com.example.app.dto.input.UpdateUserDTO;
import com.example.app.model.address.Address;
import com.example.app.model.address.AddressRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AddressService {
	@Autowired
	private final AddressRepository addressRepository;

	
	public Address get(String userId) {
		return addressRepository.getAddressById(userId);
	}

	public Address create(CreateUserDTO dto, String userId) {
		return addressRepository.save(new Address(userId, dto.getPostalCode(), dto.getStreet(),dto.getTown(), dto.getCountry()));
		
	}

	public Address update(String userId, UpdateUserDTO dto) {
		
		Address address = addressRepository.getAddressById(userId);
		
		return addressRepository.save(new Address(address.getId(), userId, dto.getPostalCode(), dto.getStreet(),dto.getTown(), dto.getCountry()));
	}

	public void remove(String userId) {
		Address address = addressRepository.getAddressById(userId);
		addressRepository.deleteById(address.getId());
	}
}
