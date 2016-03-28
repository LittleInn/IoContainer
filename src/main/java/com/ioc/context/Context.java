package com.ioc.context;

import com.ioc.proxy.ProxyCreator;

public abstract class Context {
	private ProxyCreator proxyCreator;

	public Context(ProxyCreator proxyCreator) {
		super();
		this.proxyCreator = proxyCreator;
	}


	public  void register(String packageName){
		proxyCreator.loadSingletons(packageName);
		proxyCreator.loadBeanClasses();
		proxyCreator.loadProvidedPackageClass(packageName);
	}
	public  Object getBean(String beanName){
//		return proxyCreator.getGlobalSingletonMap().get(beanName);
		return proxyCreator.getGlobalBeansMap().get(beanName);
	}
	
	public ProxyCreator getProxyCreator() {
		return proxyCreator;
	}
	
	public void setProxyCreator(ProxyCreator proxyCreator) {
		this.proxyCreator = proxyCreator;
	}
}
