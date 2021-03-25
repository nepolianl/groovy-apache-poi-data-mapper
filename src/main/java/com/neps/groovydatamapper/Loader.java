package com.neps.groovydatamapper;

import groovy.lang.Closure;

import java.util.Map;

public interface Loader extends LoaderExecutor {
    public Loader withScriptDirectory(String directory);
    public Loader withCustomMethod(String methodName, Closure<Object> closure);
    public LoaderExecutor withInputs(Map<String, Object> inputs);
}
