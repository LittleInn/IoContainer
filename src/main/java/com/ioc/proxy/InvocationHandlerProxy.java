package com.ioc.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.ioc.annotations.Inject;
import com.ioc.annotations.Singleton;
import com.ioc.context.ContextBeansHolder;

public class InvocationHandlerProxy implements InvocationHandler {

    private Object realObject;
    private ProxyCreator proxyCreator;
    private ContextBeansHolder contextBeansHolder = ContextBeansHolder.INSTANCE;

    public InvocationHandlerProxy(Object realObject, ProxyCreator proxyCreator) {
	super();
	this.realObject = realObject;
	this.proxyCreator = proxyCreator;
    }

   
	
    public Object newInstance(Object obj) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
	System.out.println("Object to Proxy: "+obj);
	Object newProxyInstance = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandlerProxy(obj, proxyCreator));
	System.out.println("PROXY: "+newProxyInstance);
	return newProxyInstance;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	Object result = null;
	System.out.println("Real object  "+realObject.getClass().getSimpleName()+" object "+realObject);
	System.out.println("Method. "+method.getName());
//if(!method.getName().equals("toString")){
    
	try {
	    Class<? extends Object> realObjectClass = realObject.getClass();
	    //scanInjectedFields();
//	    scanInjectedMethods(realObjectClass, realObject);
//	    Object object = scanInjectedConstructors(realObjectClass, realObject);
//	    if (object != null) {
//		realObject = object;
//	    }
	    result = method.invoke(realObject, args);
	} catch (InvocationTargetException e) {
	    throw e.getTargetException();
	} finally {
//	}
}
	return result;
    }

    private void scanInjectedFields() throws IllegalArgumentException, IllegalAccessException {
System.out.println("fields");
	Field[] fields = realObject.getClass().getFields();
	for (Field field : fields) {
	    field.setAccessible(true);
	    if (field.isAnnotationPresent(Inject.class)) {
		if (!field.getAnnotation(Inject.class).factory().isEmpty()) {
		    field.set(realObject, contextBeansHolder.getGlobalProvidesMap().get(field.getAnnotation(Inject.class).factory()));
		} else {
		    field.set(realObject, contextBeansHolder.getGlobalBeansMap().get(field.getAnnotation(Inject.class).service()));
		}
	    }
	}

    }

    private Object scanInjectedConstructors(Class<? extends Object> realObjectClass, Object realObject2) {
	Constructor<?>[] constructors = realObjectClass.getConstructors();
	Object constructedObj = null;
	System.out.println("- "+realObjectClass.getSimpleName());
//	System.out.println(contextBeansHolder);
	if (!realObject.getClass().isAnnotationPresent(Singleton.class) ) {
	    System.out.println(" not Singleton: " + realObject.getClass().getSimpleName());
	    for (Constructor<?> constructor : constructors) {
		if (constructor.isAnnotationPresent(Inject.class)) {
		    String annotation = constructor.getAnnotation(Inject.class).service();
		    System.out.println("annotation: "+annotation);
		    try {
			System.out.println("Map: "+contextBeansHolder.getGlobalBeansMap());
			Object object = contextBeansHolder.getGlobalBeansMap().get(annotation);
			System.out.println("- - "+object);
			constructedObj = constructor.newInstance(object);
		    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		    }
		}
	    }
	}
	return constructedObj;
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
