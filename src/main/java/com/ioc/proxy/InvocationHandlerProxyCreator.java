package com.ioc.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.ioc.annotations.Inject;
import com.ioc.context.ContextBeansHolder;

public class InvocationHandlerProxyCreator extends ProxyCreator {

    private ContextBeansHolder contextBeansHolder = ContextBeansHolder.INSTANCE;

    public Object createBeanProxy(Class<?> className) {
	Object instance = null;
	Object newInstance = null;
	try {
	    instance = className.newInstance();
	    InvocationHandlerBeanProxy frameworkProxy = new InvocationHandlerBeanProxy(instance, this);
	    newInstance = frameworkProxy.newInstance(instance);
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
	    e.printStackTrace();
	}
	return newInstance;
    }

    public Object createInitSingletonProxy(Class<?> className) {
	Object instance = null;
	Object newInstance = null;
	try {
	    instance = className.newInstance();
	    SingletonInvocationProxy frameworkProxy = new SingletonInvocationProxy(instance);
	    newInstance = frameworkProxy.newInstance(instance);
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
	    e.printStackTrace();
	}

	return newInstance;
    }

    @Override
    public Object loadInitialSingletons(Class<?> annotatedClass) {
	return createInitSingletonProxy(annotatedClass);
    }

    @Override
    public Object createFullSingletonProxy(Class<?> className) {
	Constructor<?>[] constructors = className.getConstructors();
	Object newInstance = null;
	Object constructedObj = null;
	for (Constructor<?> constructor : constructors) {
	    if (constructor.isAnnotationPresent(Inject.class)) {
		try {
		    constructor = className.getConstructor(constructor.getParameterTypes()[0]);
		    String service = constructor.getAnnotation(Inject.class).name();
		    constructedObj = constructor.newInstance(contextBeansHolder.getGlobalSingletonMap().get(service));
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
		    e.printStackTrace();
		}
	    }
	}
	InvocationHandlerBeanProxy frameworkProxy = new InvocationHandlerBeanProxy(constructedObj, this);
	try {
	    scanInjectedFields(className, constructedObj);
	    newInstance = frameworkProxy.newInstance(constructedObj);
	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
	    e.printStackTrace();
	}
	return newInstance;
    }

}
