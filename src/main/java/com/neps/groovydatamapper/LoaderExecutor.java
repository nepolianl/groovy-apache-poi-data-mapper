
package com.neps.groovydatamapper;

import java.util.Collection;
import java.util.Map;

public interface LoaderExecutor {
    public Map<String, Object> load(String loadScript, String... additionalScripts);
    public Map<String, Object> load(Script script, Script... additionalScripts);
    public Map<String, Object> load(Collection<String> loadScripts);
    public Map<String, Object> loadNamespaced(Collection<Script> loadScripts);
}
