package com.ioc.context;

import java.util.List;

import com.ioc.proxy.ProxyCreator;

public abstract class Context {
    private ProxyCreator proxyCreator;

    public Context(ProxyCreator proxyCreator) {
	super();
	this.proxyCreator = proxyCreator;
    }

    public void buildContext(List<Class> classes) {
	proxyCreator.loadBeanClasses(classes);
	proxyCreator.buildSingletonBeans(classes);
	// proxyCreator.loadProvidedPackageClass(classes);
    }

    public ProxyCreator getProxyCreator() {
	return proxyCreator;
    }

    public void setProxyCreator(ProxyCreator proxyCreator) {
	this.proxyCreator = proxyCreator;
    }
}
