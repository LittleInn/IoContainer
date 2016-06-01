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

    private ProxiesCreators creators;

    public ProxyCreator(ProxiesCreators creators) {
	this.creators = creators;
	parser = new ParseClasses();
	contextClassLoader = Thread.currentThread().getContextClassLoader();
    }

    public void buildSingletonBeans(List<Class> classes) {
	try {
	    initSingletonWithoutFields(classes);
	    createFullSingletonObjects(classes);
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SecurityException | NoSuchFieldException | IllegalArgumentException | NoSuchMethodException
		| InvocationTargetException e) {
	    e.printStackTrace();
	}
    }

    private void initSingletonWithoutFields(List<Class> classes) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SecurityException, NoSuchFieldException,
	    IllegalArgumentException, NoSuchMethodException, InvocationTargetException {
	for (Class<?> annotatedClass : classes) {
	    Annotation[] annotations = annotatedClass.getAnnotations();
	    for (Annotation annotation : annotations) {
		if (annotation instanceof Singleton) {
		    contextBeansHolder.getGlobalSingletonMap().put(annotatedClass.getSimpleName(), creators.loadInitialSingletons(annotatedClass));
		}
	    }
	}
	contextBeansHolder.getGlobalBeansMap().putAll(contextBeansHolder.getGlobalSingletonMap());
    }

    public void createFullSingletonObjects(List<Class> classes) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchFieldException,
	    NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
	for (Class<?> annotatedClass : classes) {
	    Annotation[] annotations = annotatedClass.getAnnotations();
	    for (Annotation annotation : annotations) {
		if (annotation instanceof Singleton) {
		    Object createSingletonProxy = creators.createFullSingletonProxy(annotatedClass);
		    contextBeansHolder.getGlobalSingletonMap().put(annotatedClass.getSimpleName(), createSingletonProxy);
		    contextBeansHolder.getGlobalBeansMap().put(annotatedClass.getSimpleName(), createSingletonProxy);
		}
	    }
	}
    }

    public void loadBeanClasses(List<Class> classes) {
	for (Class<?> className : classes) {
	    try {
		buildBeanProxy(className);
	    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
		e.printStackTrace();
	    }
	}
    }

    private void buildBeanProxy(Class<?> annotatedClass) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
	Annotation[] annotations = annotatedClass.getAnnotations();
	for (Annotation annotation : annotations) {
	    if (annotation instanceof Bean) {
		String name = ((Bean) annotation).name();
		// TODO
		Object proxy = creators.createBeanProxy(annotatedClass);
		contextBeansHolder.getGlobalBeansMap().put(name, proxy);
		if (annotatedClass.getInterfaces().length > 0) {
		    contextBeansHolder.getGlobalBeansMap().put(annotatedClass.getInterfaces()[0].getSimpleName(), proxy);
		} else {
		    contextBeansHolder.getGlobalBeansMap().put(annotatedClass.getSimpleName(), proxy);
		}
		break;
	    }
	}
    }

    public void loadProvided(Class<?> className) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	Method[] methods = className.getMethods();
	for (Method method : methods) {
	    if (method.isAnnotationPresent(Provides.class)) {
		Provides annotation = method.getAnnotation(Provides.class);
		String name = ((Provides) annotation).name();
		contextBeansHolder.getGlobalProvidesMap().put(name, method.invoke(className.newInstance()));
	    }
	}
    }

    public void loadProvidedPackageClass(String packageName) {
	try {
	    List<Class> classes = getParser().getClasses(getContextClassLoader(), packageName);
	    for (Class<?> className : classes) {
		loadProvided(className);
	    }
	} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException | IOException e) {
	    e.printStackTrace();
	}

    }

    public void loadProvidedPackageClass(List<Class> classes) {
	try {
	    for (Class<?> className : classes) {
		loadProvided(className);
	    }
	} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
	    e.printStackTrace();
	}

    }

    public abstract Object createBean(Class<?> annotatedClass);

    public abstract Object loadInitialSingleton(Class<?> annotatedClass);

    public abstract Object buildFullSingletonProxy(Class<?> className);

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

    public ProxiesCreators getCreators() {
	return creators;
    }

    public void setCreators(ProxiesCreators creators) {
	this.creators = creators;
    }

}
