package com.neps.groovydatamapper.impl;

public class FileSystemLoaderImpl extends AbstractLoaderImpl {

	public FileSystemLoaderImpl() {
		super();
	}
	
	@Override
	protected AbstractScriptExecutor createScriptExecutor() {
		FileSystemScriptExecutor executor = new FileSystemScriptExecutor();
	    executor.setScriptDirectory(scriptDirectory);
	    executor.setCustomMethods(customMethods);
	    return executor;
	}
}
