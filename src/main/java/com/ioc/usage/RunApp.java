package com.ioc.usage;

import com.ioc.context.ContextBuilder;
import com.ioc.impl.MailService;
import com.ioc.model.SingletonServiceA;
import com.ioc.service.UserService;

public class RunApp {
	public RunApp() {

	}

	public static void main(String[] args) {
		ContextBuilder helper=new ContextBuilder();
		helper.excecuteContext();
		
		UserService userService1 = (UserService)helper.getBean("userServiceImpl");
//		EmployeeService employeeService = (EmployeeService)helper.getBean("employeeServiceImpl");
		MailService mailService = (MailService)helper.getBean("mailService");
		mailService.printMail();
//		employeeService.addEmployeeToCompany();
		System.out.println("Bean: "+helper.getBean("userServiceImpl"));
		//userService1.createUser("Test main");
		userService1.addUserToCompany();
//		
		SingletonServiceA a1 = (SingletonServiceA)helper.getBean("SingletonServiceAImpl");
		System.out.println("Bean: "+helper.getBean("SingletonServiceAImpl"));
		a1.printInfo();
		
//		UserService userService = (UserService)helper.getBean("userServiceImpl");
//		userService.createUser("Test User");
//		SingletonServiceB b = (SingletonServiceB)helper.getBean("SingletonServiceBImpl");
//		b.provideInfo();
		
//		AnnotationApplicationfactory factory = new AnnotationApplicationfactory();
//		CGLibContext cgLibContext = factory.createCGLibContext();
//		cgLibContext.register("com/ioc/model");
		
	}
}
