package com.neps.groovydatamapper.impl;

import groovy.lang.Binding;

import java.util.HashMap;
import java.util.Map;

public class NamespacedBinding extends Binding {

    private boolean capture;
    private Map<String, Object> namespacedVariables = new HashMap<>();


    public void startCapture() {
        capture = true;
    }


    @Override
    public void setVariable(String name, Object value) {
        if (capture) {
            namespacedVariables.put(name, value);
        }
        super.setVariable(name, value);
    }


    public Map<String, Object> getNamespacedVariables() {
        return namespacedVariables;
    }

}
