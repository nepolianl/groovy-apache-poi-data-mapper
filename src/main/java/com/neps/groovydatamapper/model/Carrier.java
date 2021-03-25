package com.neps.groovydatamapper.model;

import java.util.HashMap;
import java.util.Map;

public enum Carrier {
	ICARUS("ICARUS"),
	CYPRESS("CYPRESS");
	
	private String carrier;
	private Carrier(String c) {
		this.carrier = c;
	}
	
	private static Map<String, Carrier> enumByCode = new HashMap<String, Carrier>();
	static {
		for (Carrier e : values()) {
			if (enumByCode.get(e.getCarrier()) != null) {
				throw new RuntimeException("Duplicate carrier found: " + e);
			}
			enumByCode.put(e.getCarrier(), e);
		}
	}
	
	public String getCarrier() {
		return carrier;
	}
	
	public static Carrier forCode(String code) {
		return enumByCode.get(code);
	}
}
