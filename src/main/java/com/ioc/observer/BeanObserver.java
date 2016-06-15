package com.ioc.observer;

import java.util.List;

import com.ioc.context.ContextBeansHolder;
import com.ioc.proxy.ProxiesCreators;

public interface BeanObserver {
    void createProxy(List<Class> classes, ProxiesCreators creators, ContextBeansHolder contextBeansHolder);
}
