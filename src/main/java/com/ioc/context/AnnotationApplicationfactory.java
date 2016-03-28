package com.ioc.context;

import com.ioc.proxy.CGLibProxyCreator;
import com.ioc.proxy.InvocationHandlerProxyCreator;

public class AnnotationApplicationfactory  implements AbstractApplicationContextFactory{

	//@Override
	public CGLibContext createCGLibContext() {
		return new AnnotationCGLibContext(new CGLibProxyCreator());
	}

	//@Override
	public InvocHandlerContext createInvocHandlerContext() {
		return new AnnotationInvocHandlerContext(new InvocationHandlerProxyCreator());
	}

}
