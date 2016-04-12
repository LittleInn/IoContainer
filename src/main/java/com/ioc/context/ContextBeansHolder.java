package com.ioc.context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContextBeansHolder {
    public static List<String> packages = new ArrayList<String>(Arrays.asList("com/ioc", "com/ioc/model", "com/ioc/annotations", "com/ioc/impl", "com/ioc/service", "com/ioc/usage"));

    private Map<String, Object> globalBeansMap = new HashMap<String, Object>();
    private Map<String, Object> globalSingletonMap = new HashMap<String, Object>();
    private Map<String, Object> globalProvidesMap = new HashMap<String, Object>();

    public static final ContextBeansHolder INSTANCE = new ContextBeansHolder();

    public ContextBeansHolder() {
    }

    public Map<String, Object> getGlobalBeansMap() {
	return globalBeansMap;
    }

    public void setGlobalBeansMap(Map<String, Object> globalBeansMap) {
	this.globalBeansMap = globalBeansMap;
    }

    public Map<String, Object> getGlobalSingletonMap() {
	return globalSingletonMap;
    }

    public void setGlobalSingletonMap(Map<String, Object> globalSingletonMap) {
	this.globalSingletonMap = globalSingletonMap;
    }

    public Map<String, Object> getGlobalProvidesMap() {
	return globalProvidesMap;
    }

    public void setGlobalProvidesMap(Map<String, Object> globalProvidesMap) {
	this.globalProvidesMap = globalProvidesMap;
    }
}
