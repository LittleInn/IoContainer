package com.ioc.proxy;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ioc.annotations.Bean;
import com.ioc.annotations.Provides;
import com.ioc.annotations.Singleton;
import com.ioc.context.ParseClasses;

public abstract class ProxyCreator {

	static List<String> packages = new ArrayList<String>(Arrays.asList(
			"com/ioc", "com/ioc/model", "com/ioc/annotations", "com/ioc/impl",
			"com/ioc/service", "com/ioc/usage"));

	Map<String, Object> globalBeansMap = new HashMap<String, Object>();
	Map<String, Object> globalSingletonMap = new HashMap<String, Object>();
	Map<String, Object> globalProvidesMap = new HashMap<String, Object>();

	private ParseClasses parser;
	private ClassLoader contextClassLoader;

	public ProxyCreator() {
		parser = new ParseClasses();
		contextClassLoader = Thread.currentThread().getContextClassLoader();
	}

	public void loadSingletons(String packageName) {
		try {
			List<Class> classes = getParser().getClasses(
					getContextClassLoader(), packageName);
			initSingletonMapHolder(classes);
			createSingletonObjects(classes);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SecurityException
				| NoSuchFieldException | IllegalArgumentException
				| NoSuchMethodException | InvocationTargetException
				| IOException e) {
			e.printStackTrace();
		}
	}

	private void initSingletonMapHolder(List<Class> classes)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SecurityException, NoSuchFieldException,
			IllegalArgumentException, NoSuchMethodException,
			InvocationTargetException {
		for (Class<?> annotatedClass : classes) {
			Annotation[] annotations = annotatedClass.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof Singleton) {
					getGlobalSingletonMap().put(annotatedClass.getSimpleName(),
							loadInitialSingletons(annotatedClass));
				}
			}
		}

	}

	public void createSingletonObjects(List<Class> classes)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, SecurityException, NoSuchFieldException,
			NoSuchMethodException, IllegalArgumentException,
			InvocationTargetException {
		for (Class<?> annotatedClass : classes) {
			Annotation[] annotations = annotatedClass.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof Singleton) {
					Object createSingletonProxy = createSingletonProxy(annotatedClass);
					getGlobalSingletonMap().put(annotatedClass.getSimpleName(),
							createSingletonProxy);
				}
			}
			getGlobalBeansMap().putAll(getGlobalSingletonMap());
		}
	}

	public void loadBeanClasses() {
		for (String packageName : packages) {
			List<Class> classes;
			try {
				classes = getParser().getClasses(getContextClassLoader(),
						packageName);
				for (Class<?> className : classes) {
					loadBeanProxy(className);
				}
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void loadBeanProxy(Class<?> annotatedClass)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		Annotation[] annotations = annotatedClass.getAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation instanceof Bean) {
				String name = ((Bean) annotation).name();
				Object proxy = createProxy(annotatedClass);
				getGlobalBeansMap().put(name, proxy);
				getGlobalBeansMap().put(annotatedClass.getSimpleName(), proxy);
			}

		}
	}

	public void loadProvided(Class<?> className) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Method[] methods = className.getMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(Provides.class)) {
				Provides annotation = method.getAnnotation(Provides.class);
				String name = ((Provides) annotation).name();
				getGlobalProvidesMap().put(name,
						method.invoke(className.newInstance()));

			}
		}
	}

	public void loadProvidedPackageClass(String packageName) {
		try {
			List<Class> classes = getParser().getClasses(
					getContextClassLoader(), packageName);
			for (Class<?> className : classes) {
				loadProvided(className);
			}
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

	}

	public abstract Object createProxy(Class<?> annotatedClass);

	public abstract Object loadInitialSingletons(Class<?> annotatedClass);

	public abstract Object createSingletonProxy(Class<?> className);

	public Map<String, Object> getGlobalBeansMap() {
		return globalBeansMap;
	}

	public void setGlobalBeansMap(Map<String, Object> globalBeansMap) {
		this.globalBeansMap = globalBeansMap;
	}

	public Map<String, Object> getGlobalSingletonMap() {
		return globalSingletonMap;
	}

	public void setGlobalSingletonMap(Map<String, Object> globalSingletonMap) {
		this.globalSingletonMap = globalSingletonMap;
	}

	public ParseClasses getParser() {
		return parser;
	}

	public void setParser(ParseClasses parser) {
		this.parser = parser;
	}

	public ClassLoader getContextClassLoader() {
		return contextClassLoader;
	}

	public void setContextClassLoader(ClassLoader contextClassLoader) {
		this.contextClassLoader = contextClassLoader;
	}

	public Map<String, Object> getGlobalProvidesMap() {
		return globalProvidesMap;
	}

	public void setGlobalProvidesMap(Map<String, Object> globalProvidesMap) {
		this.globalProvidesMap = globalProvidesMap;
	}

}
