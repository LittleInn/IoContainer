package com.ioc.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.ioc.annotations.Inject;

public class FrameworkProxy implements InvocationHandler {

	private Object realObject;
	private ProxyCreator proxyCreator;

	public FrameworkProxy(Object realObject, ProxyCreator proxyCreator) {
		super();
		this.realObject = realObject;
		this.proxyCreator = proxyCreator;
	}

	public Object newInstance(Object obj) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj
				.getClass().getInterfaces(), new FrameworkProxy(obj,
				proxyCreator));
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

		Object result = null;
		try {
			Class<? extends Object> realObjectClass = realObject.getClass();
			scanInjectedFields();
			scanInjectedMethods(realObjectClass, realObject);
			Object object = scanInjectedConstructors(realObjectClass,
					realObject);
			if (object != null) {
				realObject = object;
			}
			result = method.invoke(realObject, args);
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		} finally {
		}
		return result;
	}

	private void scanInjectedFields()
			throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = realObject.getClass().getFields();
		for (Field field : fields) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(Inject.class)) {
				if (!field.getAnnotation(Inject.class).factory().isEmpty()) {
					field.set(realObject, proxyCreator.getGlobalProvidesMap()
							.get(field.getAnnotation(Inject.class).factory()));
				} else {
					field.set(
							realObject,
							proxyCreator.getGlobalBeansMap()
									.get(field.getAnnotation(Inject.class)
											.service()));
				}
			}
		}

	}

	private Object scanInjectedConstructors(
			Class<? extends Object> realObjectClass, Object realObject2) {
		Constructor<?>[] constructors = realObjectClass.getConstructors();
		Object constructedObj = null;
		for (Constructor<?> constructor : constructors) {
			if (constructor.isAnnotationPresent(Inject.class)) {
				String annotation = constructor.getAnnotation(Inject.class)
						.service();
				try {
					constructedObj = constructor.newInstance(proxyCreator
							.getGlobalBeansMap().get(annotation));
				} catch (InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return constructedObj;
	}

	private void scanInjectedMethods(final Class<?> className, Object object) {
		Method[] methods = className.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().startsWith("set")
					&& method.isAnnotationPresent(Inject.class)) {
				String annotation = method.getAnnotation(Inject.class)
						.service();

				try {
					method.invoke(object,
							proxyCreator.getGlobalBeansMap().get(annotation));
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
