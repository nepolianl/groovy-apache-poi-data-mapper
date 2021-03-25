package com.neps.groovydatamapper.model;

import java.util.HashMap;
import java.util.Map;

public enum DeliveryMethod {
	STH("STH"),
	IPK("IPK");
	
	private String deliveryMethod;
	private DeliveryMethod(String c) {
		this.deliveryMethod = c;
	}
	
	private static Map<String, DeliveryMethod> enumByCode = new HashMap<String, DeliveryMethod>();
	static {
		for (DeliveryMethod e : values()) {
			if (enumByCode.get(e.getCarrier()) != null) {
				throw new RuntimeException("Duplicate delivery method found: " + e);
			}
			enumByCode.put(e.getCarrier(), e);
		}
	}
	
	public String getCarrier() {
		return deliveryMethod;
	}
	
	public static DeliveryMethod forCode(String code) {
		return enumByCode.get(code);
	}
}
