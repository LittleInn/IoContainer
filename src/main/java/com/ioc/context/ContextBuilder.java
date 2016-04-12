package com.ioc.context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ioc.annotations.Bean;
import com.ioc.annotations.Singleton;

public class ContextBuilder {

	List<Class> cgLibList = new ArrayList<Class>();
	List<Class> invocList = new ArrayList<Class>();
	private ParseClasses parser;
	private ClassLoader contextClassLoader;
	private ContextBeansHolder contextBeansHolder = ContextBeansHolder.INSTANCE;

	public ContextBuilder() {
		parser = new ParseClasses();
		contextClassLoader = Thread.currentThread().getContextClassLoader();
	}

	public Object getBean(String beanName) {
		Object bean = contextBeansHolder.getGlobalSingletonMap().get(beanName);
		if(bean!=null){
			return bean;
		}
		return contextBeansHolder.getGlobalBeansMap().get(beanName);
	}

	public void scanBeanClasses() {
		for (String packageName : ContextBeansHolder.packages) {
			List<Class> classes;
			try {
				classes = getParser().getClasses(getContextClassLoader(),
						packageName);
				for (Class<?> className : classes) {
					if (className.isAnnotationPresent(Bean.class)
							|| className.isAnnotationPresent(Singleton.class)) {
						if (className.getInterfaces().length > 0) {
							invocList.add(className);
						} else {
							cgLibList.add(className);

						}
					}
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void excecuteContext() {
		AnnotationApplicationfactory factory = new AnnotationApplicationfactory();
		InvocHandlerContext context = factory.createInvocHandlerContext();
		CGLibContext cgLibContext = factory.createCGLibContext();

		List<Class> invocList = getInvocList();
		List<Class> cglibList = getCgLibList();
		scanBeanClasses();

		System.out.println("invocList: "+invocList);
		System.out.println("cglibList: "+cglibList);
		context.buildContext(invocList);
		cgLibContext.buildContext(cglibList);
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

	public List<Class> getCgLibList() {
		return cgLibList;
	}

	public void setCgLibList(List<Class> cgLibList) {
		this.cgLibList = cgLibList;
	}

	public List<Class> getInvocList() {
		return invocList;
	}

	public void setInvocList(List<Class> invocList) {
		this.invocList = invocList;
	}

}