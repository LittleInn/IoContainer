package com.ioc.observer;

import java.lang.annotation.Annotation;
import java.util.List;

import com.ioc.annotations.Bean;
import com.ioc.context.ContextBeansHolder;
import com.ioc.proxy.ProxiesCreators;

public class SimpleBeanObserver implements BeanObserver {

    @Override
    public void createProxy(List<Class> classes, ProxiesCreators creators, ContextBeansHolder contextBeansHolder) {
	for (Class<?> className : classes) {
	    try {
		buildBeanProxy(className, creators, contextBeansHolder);
	    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
		e.printStackTrace();
	    }
	}
    }

    private void buildBeanProxy(Class<?> annotatedClass, ProxiesCreators creators, ContextBeansHolder contextBeansHolder) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
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
}
