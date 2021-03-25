package com.neps.groovydatamapper.impl;

import org.codehaus.groovy.runtime.InvokerHelper;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;

public class DelegatingGroovyObjectSupport<T> implements GroovyObject {

    private transient MetaClass metaClass;
    private T delegate;


    public DelegatingGroovyObjectSupport(T delegate) {
        super();
        this.delegate = delegate;
        metaClass = InvokerHelper.getMetaClass(delegate.getClass());
    }


    @Override
    public Object invokeMethod(String name, Object args) {
        return metaClass.invokeMethod(delegate, name, args);
    }


    @Override
    public Object getProperty(String propertyName) {
        return metaClass.getProperty(delegate, propertyName);
    }


    @Override
    public void setProperty(String propertyName, Object newValue) {
        metaClass.setProperty(delegate, propertyName, newValue);
    }


    @Override
    public MetaClass getMetaClass() {
        return metaClass;
    }


    @Override
    public void setMetaClass(MetaClass metaClass) {
        this.metaClass = metaClass;
    }

}
