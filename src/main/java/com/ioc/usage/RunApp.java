package com.ioc.usage;

import com.ioc.context.ContextHelper;
import com.ioc.impl.MailService;
import com.ioc.model.SingletonServiceA;
import com.ioc.model.SingletonServiceB;
import com.ioc.service.EmployeeService;
import com.ioc.service.UserService;

public class RunApp {
	public RunApp() {

	}

	public static void main(String[] args) {
		ContextHelper helper=new ContextHelper();
		helper.excecuteContext();
		
		UserService userService1 = (UserService)helper.getBean("userServiceImpl");
		EmployeeService employeeService = (EmployeeService)helper.getBean("employeeServiceImpl");
		MailService mailService = (MailService)helper.getBean("mailService");
		mailService.printMail();
		employeeService.addEmployeeToCompany();
		userService1.createUser("Test main");
		userService1.addUserToCompany();
		
		SingletonServiceA a1 = (SingletonServiceA)helper.getBean("SingletonServiceAImpl");
		a1.printInfo();
		
		UserService userService = (UserService)helper.getBean("userServiceImpl");
		userService.createUser("Test User");
		SingletonServiceB b = (SingletonServiceB)helper.getBean("SingletonServiceBImpl");
		b.provideInfo();
	}
}
