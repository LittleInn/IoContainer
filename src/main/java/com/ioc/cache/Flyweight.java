package com.ioc.cache;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class Flyweight {
    private static final WeakHashMap<Object, WeakReference<Object>> flyweigthElements = new WeakHashMap<Object, WeakReference<Object>>();


    public void add(Object key, Object value) {
	if(!flyweigthElements.containsKey(key)){
	    flyweigthElements.put(key, new WeakReference<Object>(key));
	}
    }
    
    public Object get(Object key){
	return flyweigthElements.get(key);
    }
}
