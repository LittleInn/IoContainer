package com.ioc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InvocationHandlerBeanProxy implements InvocationHandler {

    private Object realObject;
    private ProxyCreator proxyCreator;
    private ProxiesCreators creators;

    public InvocationHandlerBeanProxy(Object realObject, ProxyCreator proxyCreator) {
	super();
	this.realObject = realObject;
	this.proxyCreator = proxyCreator;
    }

    public InvocationHandlerBeanProxy(Object realObject, ProxiesCreators creators) {
	super();
	this.realObject = realObject;
	this.creators = creators;
    }

    public Object newInstance(Object obj) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
	return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandlerBeanProxy(obj, proxyCreator));
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	Object result = null;
	try {
	    if (!method.getName().equals("toString")) {
		Class<? extends Object> realObjectClass = realObject.getClass();
		Helper.scanInjectedFields(realObjectClass, realObject);
		Helper.scanInjectedMethods(realObjectClass, realObject);
	    }
	    result = method.invoke(realObject, args);
	} catch (InvocationTargetException e) {
	    throw e.getTargetException();
	}
	return result;
    }

    public ProxiesCreators getCreators() {
	return creators;
    }

    public void setCreators(ProxiesCreators creators) {
	this.creators = creators;
    }

}
