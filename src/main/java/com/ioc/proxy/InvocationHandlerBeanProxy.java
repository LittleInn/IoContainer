package com.ioc.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.ioc.annotations.Inject;
import com.ioc.context.ContextBeansHolder;

public class InvocationHandlerBeanProxy implements InvocationHandler {

    private Object realObject;
    private ProxyCreator proxyCreator;
    private ContextBeansHolder contextBeansHolder = ContextBeansHolder.INSTANCE;

    public InvocationHandlerBeanProxy(Object realObject, ProxyCreator proxyCreator) {
	super();
	this.realObject = realObject;
	this.proxyCreator = proxyCreator;
    }

    public Object newInstance(Object obj) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
	Object newProxyInstance = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandlerBeanProxy(obj, proxyCreator));
	return newProxyInstance;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	Object result = null;
	try {
	    if (!method.getName().equals("toString")) {
		System.out.println("----------------------------------METHOD NAME ------------------------ " + method.getName());
		scanInjectedFields();
		Class<? extends Object> realObjectClass = realObject.getClass();
		scanInjectedMethods(realObjectClass, realObject);
	    }
	    result = method.invoke(realObject, args);
	} catch (InvocationTargetException e) {
	    throw e.getTargetException();
	} finally {
	}
	return result;
    }

    private void scanInjectedFields() throws IllegalArgumentException, IllegalAccessException {
	Field[] fields = realObject.getClass().getDeclaredFields();
	for (Field field : fields) {
	    System.out.println("FIELD: " + field.getName());
	    field.setAccessible(true);
	    if (field.isAnnotationPresent(Inject.class)) {
		if (!field.getAnnotation(Inject.class).factory().isEmpty()) {
		    field.set(realObject, contextBeansHolder.getGlobalProvidesMap().get(field.getAnnotation(Inject.class).factory()));
		} else {
		    String service = field.getAnnotation(Inject.class).service();
		    if (!service.equals("")) {
			System.out.println("0000000000000000000000000000000");
			field.set(realObject, contextBeansHolder.getGlobalBeansMap().get(service));
		    } else {
			System.out.println("ELSEEEEEEEEE " + field.getType().getSimpleName());
			field.set(realObject, contextBeansHolder.getGlobalBeansMap().get(field.getType().getSimpleName()));
		    }
		}
	    }
	    // break;
	}

    }

    private void scanInjectedMethods(final Class<?> className, Object object) {
	Method[] methods = className.getDeclaredMethods();
	for (Method method : methods) {
	    if (method.getName().startsWith("set") && method.isAnnotationPresent(Inject.class)) {
		String annotation = method.getAnnotation(Inject.class).service();

		try {
		    method.invoke(object, contextBeansHolder.getGlobalBeansMap().get(annotation));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		    e.printStackTrace();
		}
	    }
	}
    }
}
