package com.ioc.impl;

import com.ioc.annotations.Bean;
import com.ioc.annotations.Inject;
import com.ioc.context.ContextBeansHolder;
import com.ioc.model.SingletonCGLIB;
import com.ioc.model.SingletonServiceA;
import com.ioc.model.User;
import com.ioc.service.CompanyService;
import com.ioc.service.UserService;

@Bean(name = "userServiceImpl")
public class UserServiceImpl implements UserService {

    private ContextBeansHolder contextBeansHolder = ContextBeansHolder.INSTANCE;

    @Inject(name = "userInstance", method = "userInstance")
    private User user;

    @Inject
    private CompanyService companyService;

    @Inject
    private SingletonCGLIB singletonCGLIB;

    @Inject(name = "SingletonServiceAImpl")
    private SingletonServiceA singletonServiceA;

    private CompanyService compService;

    @Inject(name = "mailService")
    private MailService mailService;

    public UserServiceImpl() {
    }

    public boolean createUser(String name) {
	User user = new User("Test");
	System.out.println("User created: " + name);
	return (user != null) ? true : false;
    }

    public void addUserToCompany() {
//	// test @Inject annotation by method
//	System.out.println("----------------- @Inject annotation by method -----------------");
//	compService.addUser();
//	// test @Inject by field
//	System.out.println("BeansMap: " + contextBeansHolder.getGlobalBeansMap());
//	System.out.println("----------------- @Inject by field ------------------------");
//	companyService.addUser();
//
//	// test @Inject by factory-method
//	System.out.println("------------------- @Inject by factory-method------------");
//	// System.out.println("Default user Name: " + user.getName());
//
//	// System.out.println("---------------------@Inject byCGLib");
//	mailService.printMail();
//	System.out.println("SINGLETON");
//	singletonServiceA.printInfo();
	System.out.println("SINGLETON CGLIB");
	singletonCGLIB.method();
    }

    public CompanyService getCompanyServiceMethod() {
	return compService;
    }

    @Inject(name = "companyServiceImpl")
    public void setCompanyServiceMethod(CompanyService companyServiceMethod) {
	this.compService = companyServiceMethod;
    }

}
