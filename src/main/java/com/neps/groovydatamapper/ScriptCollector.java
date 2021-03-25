package com.neps.groovydatamapper;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface ScriptCollector {
	public File toFile();
	public <T> List<T> toList();
	public ScriptCollector map(Map<String, Object> output);
	public ScriptCollector withOutputDirectory(String directory);
}
