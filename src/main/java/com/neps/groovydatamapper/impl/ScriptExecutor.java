package com.neps.groovydatamapper.impl;

import java.io.File;
import java.io.InputStream;

import com.neps.groovydatamapper.Script;

public class ScriptExecutor extends AbstractScriptExecutor {

    public ScriptExecutor() {
        super();
    }

    /*
     * (non-Javadoc)
     * @see com.eclecticlogic.pedal.loader.impl.AbstractScriptExecutor#getScriptStream(com.eclecticlogic.pedal.loader.Script)
     */
	@Override
	protected InputStream getScriptStream(Script script) {
        String filename = scriptDirectory == null || scriptDirectory.trim().length() == 0 ? script.getName()
                : scriptDirectory + File.separator + script.getName();
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
	}
}
