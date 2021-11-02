package com.example.app.model.address;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, String>{
	@Query("SELECT a FROM Address a WHERE a.userId = ?1")
	Address getAddressById(String userId);
}
