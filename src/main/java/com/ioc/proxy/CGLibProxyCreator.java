package com.ioc.proxy;

import com.ioc.context.ContextBeansHolder;

public class CGLibProxyCreator extends ProxyCreator {

    ContextBeansHolder contextBeansHolder = ContextBeansHolder.INSTANCE;

    public CGLibProxyCreator(ProxiesCreators creators) {
	super(creators);
    }

    @Override
    public Object createBean(Class<?> annotatedClass) {
	return getCreators().createBeanProxy(annotatedClass);
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