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
public class Product implements Serializable {
	private String name;
	private String description;
	private String isAppleDevice;
	private String category;
	private String capacity;
}
