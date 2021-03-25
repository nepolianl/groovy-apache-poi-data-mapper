package com.neps.groovydatamapper.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.neps.groovydatamapper.Loader;
import com.neps.groovydatamapper.LoaderExecutor;
import com.neps.groovydatamapper.Script;

import groovy.lang.Closure;

public abstract class AbstractLoaderImpl implements Loader {

    public AbstractLoaderImpl() {
        super();
    }

    protected String scriptDirectory;
    protected Map<String, Closure<Object>> customMethods = new HashMap<String, Closure<Object>>();
    
	@Override
	public Loader withScriptDirectory(String directory) {
	    scriptDirectory = directory;
	    return this;
	}

	@Override
	public Loader withCustomMethod(String methodName, Closure<Object> closure) {
	    customMethods.put(methodName, closure);
	    return this;
	}

	@Override
	public LoaderExecutor withInputs(Map<String, Object> inputs) {
		AbstractScriptExecutor executor = createScriptExecutor();
	    executor.setInputs(inputs);
	    return executor;
	}

	@Override
	public Map<String, Object> load(String loadScript, String... additionalScripts) {
	    return createScriptExecutor().load(loadScript, additionalScripts);
	}

	@Override
	public Map<String, Object> load(Script script, Script... additionalScripts) {
	    return createScriptExecutor().load(script, additionalScripts);
	}

	@Override
	public Map<String, Object> load(Collection<String> loadScripts) {
	    return createScriptExecutor().load(loadScripts);
	}

	@Override
	public Map<String, Object> loadNamespaced(Collection<Script> loadScripts) {
	    return createScriptExecutor().loadNamespaced(loadScripts);
	}

	protected abstract AbstractScriptExecutor createScriptExecutor();
}
