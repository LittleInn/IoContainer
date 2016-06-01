package com.ioc.context;

import com.ioc.proxy.CGLibCreators;
import com.ioc.proxy.CGLibProxyCreator;
import com.ioc.proxy.InvocCreators;
import com.ioc.proxy.InvocationHandlerProxyCreator;

public class XmlApplicationFactory implements AbstractApplicationContextFactory {

    @Override
    public CGLibContext createCGLibContext() {
	return new XmlCGLibContext(new CGLibProxyCreator(new CGLibCreators()));
    }

    @Override
    public InvocHandlerContext createInvocHandlerContext() {
	// TODO
	return new XmlInvocHandlerContext(new InvocationHandlerProxyCreator(new InvocCreators()));
    }

}
