package com.ioc.usage;

import com.ioc.context.AnnotationApplicationfactory;
import com.ioc.context.CGLibContext;
import com.ioc.context.InvocHandlerContext;
import com.ioc.impl.UserServiceImpl;
import com.ioc.model.SingletonServiceA;
import com.ioc.model.SingletonServiceB;
import com.ioc.service.EmployeeService;
import com.ioc.service.UserService;

public class RunApp {
	public RunApp() {

	}

	public static void main(String[] args) {
		
		System.out.println("InvocHandlerContext");
		System.out.println("--------------");
		
		AnnotationApplicationfactory factory = new AnnotationApplicationfactory();
		InvocHandlerContext context = factory.createInvocHandlerContext();
		context.register("com/ioc/impl");
		context.register("com/ioc/model");
		UserService userService1 = (UserService)context.getBean("userServiceImpl");
		EmployeeService employeeService = (EmployeeService)context.getBean("employeeServiceImpl");
		employeeService.addEmployeeToCompany();
		userService1.createUser("Test main");
		userService1.addUserToCompany();
		SingletonServiceA a1 = (SingletonServiceA)context.getBean("SingletonServiceAImpl");
		a1.printInfo();
		
		System.out.println("--------------");
		System.out.println("CGLibContext");
		System.out.println("--------------");
		context.getProxyCreator().getGlobalBeansMap().clear();
		
		CGLibContext cgLibContext = factory.createCGLibContext();
		cgLibContext.register("com/ioc/impl");
		cgLibContext.register("com/ioc/model");
		UserServiceImpl userService = (UserServiceImpl)cgLibContext.getBean("userServiceImpl");
		userService.createUser("Test User");
		SingletonServiceB b = (SingletonServiceB)cgLibContext.getBean("SingletonServiceBImpl");
		b.provideInfo();
	}
}
