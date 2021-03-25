package com.neps.groovydatamapper.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Contact implements Serializable {
	private String appleId;
	private String firstName;
	private String lastName;
	private String dob;
	private String income;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String alias;
}
