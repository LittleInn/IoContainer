package com.ioc.observer;

import java.util.ArrayList;
import java.util.List;

import com.ioc.context.ContextBeansHolder;
import com.ioc.proxy.ProxiesCreators;
import com.ioc.proxy.ProxyCreator;

public class BeanObserverSubject implements ObserverSubject {
    private List<BeanObserver> observers = new ArrayList<BeanObserver>();

    private List<Class> classes;
    private ProxiesCreators creators;
    private ContextBeansHolder contextBeansHolder;
    ProxyCreator  creator;

    public BeanObserverSubject(ProxyCreator  creator, ContextBeansHolder contextBeansHolder) {
	super();
	this.creator = creator;
	this.contextBeansHolder = contextBeansHolder;
	this.creators=creator.getCreators();
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    @Override
    public void attach(BeanObserver beanObserver) {
	observers.add(beanObserver);

    }

    @Override
    public void detach(BeanObserver beanObserver) {
	observers.remove(beanObserver);

    }

    @Override
    public void notifyBeanObserver() {
	for(BeanObserver beanObserver: observers){
	    beanObserver.createProxy(classes, creators, contextBeansHolder);
	}
	
    }
}
