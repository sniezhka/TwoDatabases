package com.example.app.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.app.model.user.User;

public class UserTest {

	@Test
	void shouldUserCreateSuccessfully() {
		String firstName = "Sasha";
		String middleName = "Ivanovich";
		String lastName = "Strebchuk";

		User user = new User(firstName, middleName, lastName);

		assertThat(user.getFirstName()).as("check first name").isEqualTo(firstName);
		assertThat(user.getMiddleName()).as("check middle name").isEqualTo(middleName);
		assertThat(user.getLastName()).as("check last name").isEqualTo(lastName);
	}
	
	

}
