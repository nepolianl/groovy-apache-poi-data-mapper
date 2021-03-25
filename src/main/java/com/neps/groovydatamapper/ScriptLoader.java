package com.neps.groovydatamapper;

import java.io.File;
import java.util.List;

public interface ScriptLoader {
	public ScriptLoader withScriptDirectory(String directory);
	public ScriptLoader withOutputDirectory(String directory);
	public ScriptLoader load(Script script, Script...scripts);
	public <T> List<T> toList(ScriptCollector collector);
	public File toFile(ScriptCollector collector);
}
