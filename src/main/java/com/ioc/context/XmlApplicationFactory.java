package com.ioc.context;

import com.ioc.proxy.CGLibProxyCreator;
import com.ioc.proxy.InvocationHandlerProxyCreator;

public class XmlApplicationFactory implements AbstractApplicationContextFactory{

	//@Override
	public CGLibContext createCGLibContext() {
		return new XmlCGLibContext(new CGLibProxyCreator());
	}

	//@Override
	public InvocHandlerContext createInvocHandlerContext() {
		return new XmlInvocHandlerContext(new InvocationHandlerProxyCreator());
	}

}
