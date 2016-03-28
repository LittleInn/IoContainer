package com.ioc.context;

public interface AbstractApplicationContextFactory {
	CGLibContext createCGLibContext();

	InvocHandlerContext createInvocHandlerContext();

}
