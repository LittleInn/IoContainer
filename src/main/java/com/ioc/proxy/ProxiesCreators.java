package com.ioc.proxy;

public interface ProxiesCreators {

    Object createBeanProxy(Class<?> className);

    Object createFullSingletonProxy(Class<?> className);

    Object loadInitialSingletons(Class<?> annotatedClass);
}
