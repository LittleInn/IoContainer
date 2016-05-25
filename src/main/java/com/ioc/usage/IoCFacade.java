package com.ioc.usage;

import com.ioc.context.ContextExcecutor;
import com.ioc.impl.MailService;
import com.ioc.model.SingletonServiceA;
import com.ioc.service.UserService;

public class IoCFacade {
    public IoCFacade() {
    }

    public void processClientRequest() {
	ContextExcecutor builder = new ContextExcecutor();
	builder.excecuteContext();

	UserService userService1 = (UserService) builder.getBean("userServiceImpl");
	// EmployeeService employeeService =
	// (EmployeeService)builder.getBean("employeeServiceImpl");
	MailService mailService = (MailService) builder.getBean("mailService");
	mailService.printMail();
	// employeeService.addEmployeeToCompany();
	System.out.println("Bean: " + builder.getBean("userServiceImpl"));
	// userService1.createUser("Test main");
	userService1.addUserToCompany();
	//
	SingletonServiceA a1 = (SingletonServiceA) builder.getBean("SingletonServiceAImpl");
	System.out.println("Bean: " + builder.getBean("SingletonServiceAImpl"));
	a1.printInfo();

	// UserService userService =
	// (UserService)builder.getBean("userServiceImpl");
	// userService.createUser("Test User");
	// SingletonServiceB b =
	// (SingletonServiceB)builder.getBean("SingletonServiceBImpl");
	// b.provideInfo();

	// AnnotationApplicationfactory factory = new
	// AnnotationApplicationfactory();
	// CGLibContext cgLibContext = factory.createCGLibContext();
	// cgLibContext.register("com/ioc/model");
    }
}
