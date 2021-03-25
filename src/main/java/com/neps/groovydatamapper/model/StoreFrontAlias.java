package com.neps.groovydatamapper.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StoreFrontAlias implements Serializable {
	private String alias;
	private String geo;
	private String segment;
	private String storeFrontId;
}
