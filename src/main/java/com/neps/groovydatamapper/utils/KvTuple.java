package com.neps.groovydatamapper.utils;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class KvTuple implements Serializable {
	private int sequence;
	private String key;
	private String display;
	private Object value;
	
	private KvTuple() {}
	
	public KvTuple(String key, Object value) {
		this.key = key;
		this.value = value;
	}
	
	public KvTuple(int serial, String key, Object value) {
		this(key, value);
		this.sequence = serial;
	}
	
	public KvTuple(int serial, String key, Object value, String display) {
		this(serial, key, value);
		this.display = display;
	}
	
	@Override
	public String toString() {
		return this.sequence 
				+ ":"
				+ this.key
				+ ((this.display != null) ? "(" + this.display +")" : "")
				+ ":"
				+ this.value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof KvTuple)) return false;
		
		KvTuple o = (KvTuple) obj;
		return this.key.equals(o.key) && this.value.equals(o.value);
	}
	
	@Override
	public int hashCode() {
		return this.key.hashCode() ^ this.value.hashCode();
	}
}
