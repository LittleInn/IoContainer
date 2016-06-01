package com.ioc.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.ioc.annotations.Inject;
import com.ioc.context.ContextBeansHolder;

public class Helper {
    private static ContextBeansHolder contextBeansHolder = ContextBeansHolder.INSTANCE;
    protected static void scanInjectedFields(Class<?> className, Object realObject) throws IllegalArgumentException, IllegalAccessException {
   	Field[] fields = className.getDeclaredFields();
   	for (Field field : fields) {
   	    setFieldValue(realObject, field);
   	}

       }
    private static void setFieldValue(Object realObject, Field field) throws IllegalAccessException {
	field.setAccessible(true);
	if (field.isAnnotationPresent(Inject.class)) {
	if (!field.getAnnotation(Inject.class).method().isEmpty()) {
	    Object object = contextBeansHolder.getGlobalProvidesMap().get(field.getAnnotation(Inject.class).method());
	    scanInjectedFields(object.getClass(), object);
	    field.set(realObject, contextBeansHolder.getGlobalProvidesMap().get(field.getAnnotation(Inject.class).method()));
	} else {
	    String name = field.getAnnotation(Inject.class).name();
	    if (!name.equals("")) {
		field.set(realObject, contextBeansHolder.getGlobalBeansMap().get(name));
	    } else {
		field.set(realObject, contextBeansHolder.getGlobalBeansMap().get(field.getType().getSimpleName()));
	    }
	}
	}
    }
    protected static void scanInjectedMethods(final Class<?> className, Object object) {
   	Method[] methods = className.getDeclaredMethods();
   	for (Method method : methods) {
   	    if (method.getName().startsWith("set") && method.isAnnotationPresent(Inject.class)) {
   		String name = method.getAnnotation(Inject.class).name();
   		try {
   		    method.invoke(object, contextBeansHolder.getGlobalBeansMap().get(name));
   		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
   		    e.printStackTrace();
   		}
   	    }
   	}
       }
}
