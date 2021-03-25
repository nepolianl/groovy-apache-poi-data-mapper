package com.neps.groovydatamapper.impl;

import com.neps.groovydatamapper.Loader;

public class LoaderImpl extends AbstractLoaderImpl implements Loader {

    public LoaderImpl() {
        super();
    }
    @Override
	protected ScriptExecutor createScriptExecutor() {
	    ScriptExecutor executor = new ScriptExecutor();
	    executor.setScriptDirectory(scriptDirectory);
	    executor.setCustomMethods(customMethods);
	    return executor;
	}
}
