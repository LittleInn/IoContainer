package com.ioc.usage;

import com.ioc.cache.CacheBuilder;
import com.ioc.context.ContextExcecutor;
import com.ioc.context.ContextExcecutorAdapter;
import com.ioc.model.SingletonServiceA;
import com.ioc.model.User;

public class IoCFacade {
    public IoCFacade() {
    }

    public void processClientRequest() {
	ContextExcecutor builder = new ContextExcecutorAdapter();
	builder.excecuteContext();
	
	testSingleton(builder);
	//testCache();

//	UserService userService1 = (UserService) builder.getBean("userServiceImpl");
//	// EmployeeService employeeService =
//	// (EmployeeService)builder.getBean("employeeServiceImpl");
//	MailService mailService = (MailService) builder.getBean("mailService");
//	mailService.printMail();
//	// employeeService.addEmployeeToCompany();
//	System.out.println("Bean: " + builder.getBean("userServiceImpl"));
//	// userService1.createUser("Test main");
//	userService1.addUserToCompany();
	//
	


    }
    private void testSingleton(ContextExcecutor builder){
	SingletonServiceA singletonA1 = (SingletonServiceA) builder.getBean("SingletonServiceAImpl");
	System.out.println("Singleton Service A Bean: " + builder.getBean("SingletonServiceAImpl"));
	singletonA1.printInfo();
    }
    private void testCache(){
	CacheBuilder cacheBuilder = new CacheBuilder();
	for (int i = 0; i < 100; i++) {
	    User user = new User(i, "User #" + i);
	    cacheBuilder.add(i, user);
	}
	User user10 = (User) cacheBuilder.get(10);
	User user1990 = (User) cacheBuilder.get(1990);
	System.out.println("User #10: " + user10.getName());
	System.out.println("User: #1990" + user1990.getName());
    }
}
