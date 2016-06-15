package com.ioc.context;

import java.util.List;

import com.ioc.observer.BeanObserverSubject;
import com.ioc.observer.ObserverSubject;
import com.ioc.observer.SimpleBeanObserver;
import com.ioc.observer.SingletonBeanObserver;
import com.ioc.proxy.ProxyCreator;

public abstract class Context {
    private ProxyCreator proxyCreator;
    private ContextBeansHolder contextBeansHolder = ContextBeansHolder.INSTANCE;
    private ObserverSubject observerSubj;
    private BeanObserverSubject beanObserverSubject;

    public Context(ProxyCreator proxyCreator) {
	super();
	beanObserverSubject = new BeanObserverSubject(proxyCreator, contextBeansHolder);
	this.proxyCreator = proxyCreator;
    }

    public void buildContext(List<Class> classes) {
//	proxyCreator.loadBeanClasses(classes);
//	proxyCreator.buildSingletonBeans(classes);
	// proxyCreator.loadProvidedPackageClass(classes);
	
	System.out.println("Observer~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	beanObserverSubject.setClasses(classes);
	beanObserverSubject.attach(new SimpleBeanObserver());
	beanObserverSubject.attach(new SingletonBeanObserver());
	beanObserverSubject.notifyBeanObserver();
    }

    public ProxyCreator getProxyCreator() {
	return proxyCreator;
    }

    public void setProxyCreator(ProxyCreator proxyCreator) {
	this.proxyCreator = proxyCreator;
    }
}
