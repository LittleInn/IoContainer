package com.ioc.context;

import java.util.List;

import com.ioc.proxy.ProxyCreator;

public abstract class Context {
	private ProxyCreator proxyCreator;

	public Context(ProxyCreator proxyCreator) {
		super();
		this.proxyCreator = proxyCreator;
	}

	public void register(String packageName) {
		proxyCreator.loadSingletons(packageName);
		proxyCreator.loadBeanClasses();
		proxyCreator.loadProvidedPackageClass(packageName);
	}

	public void register(List<Class> classes) {
		proxyCreator.loadSingletons(classes);
		proxyCreator.loadBeanClasses(classes);
		proxyCreator.loadProvidedPackageClass(classes);
	}

	public ProxyCreator getProxyCreator() {
		return proxyCreator;
	}

	public void setProxyCreator(ProxyCreator proxyCreator) {
		this.proxyCreator = proxyCreator;
	}
}
