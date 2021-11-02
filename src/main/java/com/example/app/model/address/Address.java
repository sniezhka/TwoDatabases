package com.example.app.model.address;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "address")
@Data
@Getter
@Setter
public class Address {
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	private String userId;
	private String postalCode;
	private String street;
	private String town;
	private String country;

	public Address() {
	}
	
	public Address(String id, String userId, String postalCode, String street, String town, String country) {
		this.id = id;
		this.userId = userId;
		this.postalCode = postalCode;
		this.street = street;
		this.town = town;
		this.country = country;
	}
	public Address(String userId, String postalCode, String street, String town, String country) {
		this.userId = userId;
		this.postalCode = postalCode;
		this.street = street;
		this.town = town;
		this.country = country;
	}
	
}
