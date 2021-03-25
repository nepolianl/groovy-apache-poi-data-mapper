package com.neps.groovydatamapper.impl;

import com.neps.groovydatamapper.ScriptCollector;
import com.neps.groovydatamapper.utils.ExportFileType;

public final class ScriptCollectors {
	private ScriptCollectors() {}
	
	public static ScriptCollector toList(Class<?> clazz) {
		return new ScriptCollectorImpl(clazz);
	}
	
	public static ScriptCollector toList(Class<?> clazz, String... namespaces) {
		return new ScriptCollectorImpl(clazz, namespaces);
	}
	
	public static ScriptCollector toFile(ExportFileType fileType, Class<?> clazz) {
		return new ScriptCollectorImpl(fileType, clazz, null);
	}
	
	public static ScriptCollector toFile(ExportFileType fileType, Class<?> clazz, String fileName) {
		return new ScriptCollectorImpl(fileType, clazz, fileName);
	}
}
