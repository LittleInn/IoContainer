package com.ioc.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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
	System.out.println("Create Bean Proxy: " + newInstance);
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
    public Object createSingletonProxy(Class<?> className) {
	Constructor<?>[] constructors = className.getConstructors();
	Object newInstance = null;
	for (Constructor<?> constructor : constructors) {
	    if (constructor.isAnnotationPresent(Inject.class)) {
		Object constructedObj = null;
		try {
		    constructor = className.getConstructor(constructor.getParameterTypes()[0]);
		    String service = constructor.getAnnotation(Inject.class).service();
		    constructedObj = constructor.newInstance(contextBeansHolder.getGlobalSingletonMap().get(service));
		    for (Field field : className.getFields()) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(Inject.class)) {
			    String beanField = field.getAnnotation(Inject.class).service();
			    try {
				field.set(constructedObj, contextBeansHolder.getGlobalBeansMap().get(beanField));
			    } catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			    }
			}
		    }
		    InvocationHandlerBeanProxy frameworkProxy = new InvocationHandlerBeanProxy(constructedObj, this);
		    newInstance = frameworkProxy.newInstance(constructedObj);

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
		    e.printStackTrace();
		}
	    }
	}
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~ NEW INSTANCE ~~~~~~~~~~~~~~~~ "+newInstance);
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~ NEW INSTANCE ~~~~~~~~~~~~~~~~ "+newInstance);
	return newInstance;
    }

}
