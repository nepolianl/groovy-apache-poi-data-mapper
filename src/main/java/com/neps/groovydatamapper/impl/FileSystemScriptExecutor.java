package com.neps.groovydatamapper.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.neps.groovydatamapper.Script;
public class FileSystemScriptExecutor extends AbstractScriptExecutor {

	protected FileSystemScriptExecutor() {
		super();
	}

	@Override
	protected InputStream getScriptStream(Script script) 
		throws IOException 
	{
		File scriptSource = null;
		if (scriptDirectory == null || scriptDirectory.trim().length() == 0) {
			scriptSource = new File(script.getName());
		} else {
			File directory = new File(scriptDirectory.trim());
			scriptSource = new File(directory, script.getName());
		}

        return new FileInputStream(scriptSource);
	}
}
