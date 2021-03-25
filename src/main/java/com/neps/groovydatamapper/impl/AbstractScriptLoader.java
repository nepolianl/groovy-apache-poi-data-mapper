package com.neps.groovydatamapper.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.neps.groovydatamapper.Script;
import com.neps.groovydatamapper.ScriptCollector;
import com.neps.groovydatamapper.ScriptLoader;

public abstract class AbstractScriptLoader implements ScriptLoader {

	public AbstractScriptLoader() {
		super();
	}
	
	protected abstract AbstractLoaderImpl loader();
	
	protected String outputDirectory, scriptDirectory;
	
	private transient Map<String, Object> output;
	
	@Override
	public ScriptLoader withScriptDirectory(String directory) {
		this.scriptDirectory = directory;
		this.loader().withScriptDirectory(directory);
		return this;
	}

	@Override
	public ScriptLoader withOutputDirectory(String directory) {
		this.outputDirectory = directory;
		return this;
	}

	@Override
	public ScriptLoader load(Script script, Script... scripts) {
		if (script == null && (scripts == null || scripts.length == 0)) {
			throw new RuntimeException("Provide script(s) to continue");
		}
		this.output = this.loader().load(script, scripts);
		return this;
	}

	@Override
	public <T> List<T> toList(ScriptCollector collector) {
		return collector.withOutputDirectory(this.outputDirectory).map(this.output).toList();
	}

	@Override
	public File toFile(ScriptCollector collector) {
		return collector.withOutputDirectory(this.outputDirectory).map(this.output).toFile();
	}

}
