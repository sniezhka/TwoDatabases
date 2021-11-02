package com.example.app.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {
	private String firstName;
	private String middleName;
	private String lastName;
	private String postalCode;
	private String street;
	private String town;
	private String country;
}
