package com.example.app.dto.output;

import com.example.app.model.address.Address;
import com.example.app.model.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	private String id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String postalCode;
	private String street;
	private String town;
	private String country;
	
	public UserDTO(User user, Address address) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.middleName = user.getMiddleName();
		this.lastName = user.getLastName();
		this.postalCode = address.getPostalCode();
		this.street = address.getStreet();
		this.town = address.getTown();
		this.country = address.getCountry();
	}
}
