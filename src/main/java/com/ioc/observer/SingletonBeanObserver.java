package com.ioc.observer;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.ioc.annotations.Singleton;
import com.ioc.context.ContextBeansHolder;
import com.ioc.proxy.ProxiesCreators;

public class SingletonBeanObserver implements BeanObserver{

    @Override
    public void createProxy(List<Class> classes, ProxiesCreators creators, ContextBeansHolder contextBeansHolder) {
	try {
	    initSingletonWithoutFields(classes, creators,contextBeansHolder);
	    createFullSingletonObjects(classes,creators,contextBeansHolder);
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SecurityException | NoSuchFieldException | IllegalArgumentException | NoSuchMethodException
		| InvocationTargetException e) {
	    e.printStackTrace();
	}
	
    }
    private void initSingletonWithoutFields(List<Class> classes, ProxiesCreators creators, ContextBeansHolder contextBeansHolder) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SecurityException, NoSuchFieldException,
    IllegalArgumentException, NoSuchMethodException, InvocationTargetException {
for (Class<?> annotatedClass : classes) {
    Annotation[] annotations = annotatedClass.getAnnotations();
    for (Annotation annotation : annotations) {
	if (annotation instanceof Singleton) {
	    contextBeansHolder.getGlobalSingletonMap().put(annotatedClass.getSimpleName(), creators.loadInitialSingletons(annotatedClass));
	}
    }
}
contextBeansHolder.getGlobalBeansMap().putAll(contextBeansHolder.getGlobalSingletonMap());
}

public void createFullSingletonObjects(List<Class> classes, ProxiesCreators creators, ContextBeansHolder contextBeansHolder) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchFieldException,
    NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
for (Class<?> annotatedClass : classes) {
    Annotation[] annotations = annotatedClass.getAnnotations();
    for (Annotation annotation : annotations) {
	if (annotation instanceof Singleton) {
	    Object createSingletonProxy = creators.createFullSingletonProxy(annotatedClass);
	    contextBeansHolder.getGlobalSingletonMap().put(annotatedClass.getSimpleName(), createSingletonProxy);
	    contextBeansHolder.getGlobalBeansMap().put(annotatedClass.getSimpleName(), createSingletonProxy);
	}
    }
}
}
}
