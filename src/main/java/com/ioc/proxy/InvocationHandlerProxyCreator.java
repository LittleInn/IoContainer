package com.ioc.proxy;

public class InvocationHandlerProxyCreator extends ProxyCreator {

    public InvocationHandlerProxyCreator(ProxiesCreators creators) {
	super(creators);
    }

    public Object createBean(Class<?> className) {
	return getCreators().createBeanProxy(className);
    }

    @Override
    public Object loadInitialSingleton(Class<?> annotatedClass) {
	return getCreators().loadInitialSingletons(annotatedClass);
    }

    @Override
    public Object buildFullSingletonProxy(Class<?> className) {
	return getCreators().createFullSingletonProxy(className);
    }

}
