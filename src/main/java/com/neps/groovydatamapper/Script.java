package com.neps.groovydatamapper;

public class Script {

    private String name;
    private String namespace;

    public static Script with(String name, String namespace) {
        Script script = new Script();
        script.name = name;
        script.namespace = namespace;
        return script;
    }

    public static Script script(String name) {
        return with(name, null);
    }

    public String getName() {
        return name;
    }

    public String getNamespace() {
        return namespace;
    }
}
