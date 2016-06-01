package com.ioc.context;

import com.ioc.proxy.InvocationHandlerProxyCreator;

public abstract class InvocHandlerContext extends Context {
    public InvocHandlerContext(InvocationHandlerProxyCreator proxyCreator) {
	super(proxyCreator);
    }
}
