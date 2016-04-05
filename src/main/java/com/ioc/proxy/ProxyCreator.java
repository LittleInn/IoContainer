package com.ioc.proxy;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.ioc.annotations.Bean;
import com.ioc.annotations.Provides;
import com.ioc.annotations.Singleton;
import com.ioc.context.ContextBeansHolder;
import com.ioc.context.ParseClasses;

public abstract class ProxyCreator {

	private ContextBeansHolder contextBeansHolder = ContextBeansHolder.INSTANCE;

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
	public void loadSingletons(List<Class> classes) {
		try {
			initSingletonMapHolder(classes);
			createSingletonObjects(classes);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SecurityException
				| NoSuchFieldException | IllegalArgumentException
				| NoSuchMethodException | InvocationTargetException
				e) {
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
					contextBeansHolder.getGlobalSingletonMap().put(annotatedClass.getSimpleName(),
							loadInitialSingletons(annotatedClass));
//					getGlobalSingletonMap().put(annotatedClass.getSimpleName(),
//							loadInitialSingletons(annotatedClass));
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
					contextBeansHolder.getGlobalSingletonMap().put(annotatedClass.getSimpleName(),
							createSingletonProxy);
				}
			}
			contextBeansHolder.getGlobalBeansMap().putAll(contextBeansHolder.getGlobalSingletonMap());
		}
	}

	public void loadBeanClasses() {
		for (String packageName : contextBeansHolder.INSTANCE.packages) {
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
	public void loadBeanClasses(List<Class> classes) {
				for (Class<?> className : classes) {
					try {
						loadBeanProxy(className);
					} catch (InstantiationException | IllegalAccessException
							| ClassNotFoundException e) {
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
				contextBeansHolder.getGlobalBeansMap().put(name, proxy);
				contextBeansHolder.getGlobalBeansMap().put(annotatedClass.getSimpleName(), proxy);
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
				contextBeansHolder.getGlobalProvidesMap().put(name,
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
	public void loadProvidedPackageClass(List<Class> classes) {
		try {
			for (Class<?> className : classes) {
				loadProvided(className);
			}
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				 e) {
			e.printStackTrace();
		}
		
	}

	public abstract Object createProxy(Class<?> annotatedClass);

	public abstract Object loadInitialSingletons(Class<?> annotatedClass);

	public abstract Object createSingletonProxy(Class<?> className);

//	public Map<String, Object> getGlobalBeansMap() {
//		return globalBeansMap;
//	}
//
//	public void setGlobalBeansMap(Map<String, Object> globalBeansMap) {
//		this.globalBeansMap = globalBeansMap;
//	}
//
//	public Map<String, Object> getGlobalSingletonMap() {
//		return globalSingletonMap;
//	}
//
//	public void setGlobalSingletonMap(Map<String, Object> globalSingletonMap) {
//		this.globalSingletonMap = globalSingletonMap;
//	}

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

//	public Map<String, Object> getGlobalProvidesMap() {
//		return globalProvidesMap;
//	}
//
//	public void setGlobalProvidesMap(Map<String, Object> globalProvidesMap) {
//		this.globalProvidesMap = globalProvidesMap;
//	}

}
