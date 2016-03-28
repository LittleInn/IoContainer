package com.ioc.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;

import com.ioc.annotations.Inject;

public class CGLibProxyCreator extends ProxyCreator {

	public CGLibProxyCreator() {
		super();
	}

	public Object loadInitialSingletons(Class<?> annotatedClass) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(annotatedClass);
		enhancer.setCallback(NoOp.INSTANCE);
		return enhancer.create();

	}

	public Object createSingletonProxy(Class<?> className) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(className);
		enhancer.setCallback(NoOp.INSTANCE);

		Object newInstance = null;

		Constructor<?>[] constructors = className.getConstructors();
		Object[] constructorArguments = new Object[1];
		for (Constructor<?> c : constructors) {
			if (c.isAnnotationPresent(Inject.class)) {
				Constructor<?> constructor = null;
				String service = c.getAnnotation(Inject.class)
						.service();
				constructorArguments[0] = getGlobalSingletonMap().get(
						service);
				try {
					constructor = className.getConstructor(c
							.getParameterTypes()[0]);
					newInstance = enhancer.create(
							constructor.getParameterTypes(),
							constructorArguments);
				} catch (NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}
		return newInstance;
	}

	public Object createProxy(final Class<?> className) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(className);
		enhancer.setCallback(new MethodInterceptor() {

			public Object intercept(Object obj, Method method, Object[] args,
					MethodProxy proxy) throws Throwable {
				Field[] fields = obj.getClass().getFields();
				for (Field field : fields) {
					field.setAccessible(true);
					if (field.isAnnotationPresent(Inject.class)) {
						if (!field.getAnnotation(Inject.class).factory()
								.isEmpty()) {
							field.set(
									obj,
									getGlobalProvidesMap().get(
											field.getAnnotation(Inject.class)
													.factory()));
						} else {
							field.set(
									obj,
									getGlobalBeansMap().get(
											field.getAnnotation(Inject.class)
													.service()));
						}
					}
				}
				
				return proxy.invokeSuper(obj, args);
			}
		});
		return enhancer.create();
	}
}