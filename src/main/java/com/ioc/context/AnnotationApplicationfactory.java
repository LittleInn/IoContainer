package com.ioc.context;

import com.ioc.proxy.CGLibCreators;
import com.ioc.proxy.CGLibProxyCreator;
import com.ioc.proxy.InvocCreators;
import com.ioc.proxy.InvocationHandlerProxyCreator;

public class AnnotationApplicationfactory implements AbstractApplicationContextFactory {

    @Override
    public CGLibContext createCGLibContext() {
	return new AnnotationCGLibContext(new CGLibProxyCreator(new CGLibCreators()));
    }

    @Override
    public InvocHandlerContext createInvocHandlerContext() {
	return new AnnotationInvocHandlerContext(new InvocationHandlerProxyCreator(new InvocCreators()));
    }

}
