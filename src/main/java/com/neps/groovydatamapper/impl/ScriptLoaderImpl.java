package com.neps.groovydatamapper.impl;

import com.neps.groovydatamapper.ScriptLoader;

public class ScriptLoaderImpl extends AbstractScriptLoader implements ScriptLoader {

	public ScriptLoaderImpl() {
		super();
	}
	
	@Override
	protected AbstractLoaderImpl loader() {
		LoaderImpl loader = new LoaderImpl();
		loader.scriptDirectory = scriptDirectory;
		return loader;
	}

}
