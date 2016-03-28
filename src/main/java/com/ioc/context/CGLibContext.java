package com.ioc.context;

import com.ioc.proxy.CGLibProxyCreator;

public abstract class CGLibContext extends Context {
	public CGLibContext(CGLibProxyCreator proxyCreator) {
		super(proxyCreator);
	}
}
