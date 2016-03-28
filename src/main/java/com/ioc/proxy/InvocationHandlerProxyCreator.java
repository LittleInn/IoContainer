package com.ioc.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.ioc.annotations.Inject;

public class InvocationHandlerProxyCreator extends ProxyCreator {

	public Object createProxy(Class<?> className) {
		Object instance = null;
		Object newInstance = null;
		try {
			instance = className.newInstance();
			FrameworkProxy frameworkProxy = new FrameworkProxy(instance, this);
			newInstance = frameworkProxy.newInstance(instance);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}

		return newInstance;
	}

	@Override
	public Object loadInitialSingletons(Class<?> annotatedClass) {
		Object proxy = createProxy(annotatedClass);
		return proxy;
	}

	@Override
	public Object createSingletonProxy(Class<?> className) {
		Constructor<?>[] constructors = className.getConstructors();
		Object newInstance = null;

		for (Constructor<?> constructor : constructors) {
			if (constructor.isAnnotationPresent(Inject.class)) {
				Object constructedObj = null;
				try {
					constructor = className.getConstructor(constructor
							.getParameterTypes()[0]);
					String service = constructor.getAnnotation(Inject.class)
							.service();
					constructedObj = constructor
							.newInstance(getGlobalSingletonMap().get(service));
					FrameworkProxy frameworkProxy = new FrameworkProxy(
							constructedObj, this);
					newInstance = frameworkProxy.newInstance(constructedObj);
					
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException | NoSuchMethodException
						| SecurityException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return newInstance;
	}

}
