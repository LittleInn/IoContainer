package com.ioc.cache;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class CacheBuilder {
    private static final WeakHashMap<Object, WeakReference<Object>> cacheMap = new WeakHashMap<Object, WeakReference<Object>>();

    public void add(Object key, Object value) {
	if (!cacheMap.containsKey(key)) {
	    cacheMap.put(key, new WeakReference<Object>(value));
	}
    }

    public Object get(Object key) {
	WeakReference<Object> weakReference = cacheMap.get(key);
	if (weakReference == null)
	    return null;
	Object object = weakReference.get();
	if (object != null)
	    return object;
	return null;
    }
}
