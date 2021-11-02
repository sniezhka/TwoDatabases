package com.example.app.address;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.app.model.address.Address;

public class AddressTest {

	@Test
	void shouldUserCreateSuccessfully() {
		String userId = "ff35300nhsssaaaa";
		String postalCode = "111";
		String street = "Street";
		String town = "Town";
		String country = "Country";

		Address address = new Address(userId, postalCode, street, town, country);

		assertThat(address.getUserId()).as("check user id").isEqualTo(userId);
		assertThat(address.getPostalCode()).as("check postal code").isEqualTo(postalCode);
		assertThat(address.getStreet()).as("check street").isEqualTo(street);
		assertThat(address.getTown()).as("check town").isEqualTo(town);
		assertThat(address.getCountry()).as("check country").isEqualTo(country);
	}
	
}
