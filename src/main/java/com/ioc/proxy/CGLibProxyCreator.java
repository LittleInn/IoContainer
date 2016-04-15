package com.ioc.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;

import com.ioc.annotations.Inject;
import com.ioc.context.ContextBeansHolder;

public class CGLibProxyCreator extends ProxyCreator {
    ContextBeansHolder contextBeansHolder = ContextBeansHolder.INSTANCE;

    public CGLibProxyCreator() {
	super();
    }

    public Object loadInitialSingletons(Class<?> annotatedClass) {
	Enhancer enhancer = new Enhancer();
	enhancer.setSuperclass(annotatedClass);
	enhancer.setCallback(NoOp.INSTANCE);
	return enhancer.create();
    }

    public Object createFullSingletonProxy(Class<?> className) {
	Enhancer enhancer = new Enhancer();
	enhancer.setSuperclass(className);
	enhancer.setCallback(NoOp.INSTANCE);

	Object newInstance = null;

	Constructor<?>[] constructors = className.getConstructors();
	Constructor<?> constructor = null;
	Object[] constructorArguments = new Object[1];
	for (Constructor<?> c : constructors) {
	    if (c.isAnnotationPresent(Inject.class)) {
		String service = c.getAnnotation(Inject.class).name();
		constructorArguments[0] = contextBeansHolder.getGlobalSingletonMap().get(service);
		try {
		    constructor = className.getConstructor(c.getParameterTypes()[0]);
		    newInstance = enhancer.create(constructor.getParameterTypes(), constructorArguments);
		} catch (NoSuchMethodException | SecurityException e) {
		    e.printStackTrace();
		}

	    }
	}
	try {
	    if (newInstance == null) {
		newInstance = enhancer.create();
		scanInjectedFields(className, newInstance);
	    }
	} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
	    e.printStackTrace();
	}
	return newInstance;
    }

    public Object createBeanProxy(final Class<?> className) {
	Enhancer enhancer = new Enhancer();
	enhancer.setSuperclass(className);
	enhancer.setCallback(new MethodInterceptor() {

	    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		scanInjectedFields(className, obj);
		scanInjectedMethods(className, obj);
		return proxy.invokeSuper(obj, args);
	    }
	});
	return enhancer.create();
    }
}