package com.ioc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SingletonInvocationProxy implements InvocationHandler {

    private Object realObject;

    public SingletonInvocationProxy(Object realObject) {
	super();
	this.realObject = realObject;
    }

    public Object newInstance(Object obj) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
	Object newProxyInstance = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new SingletonInvocationProxy(obj));
	return newProxyInstance;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

	Object result = null;
	result = method.invoke(realObject, args);
	return result;
    }
}
