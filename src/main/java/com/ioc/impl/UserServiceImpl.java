package com.ioc.impl;

import com.ioc.annotations.Bean;
import com.ioc.annotations.Inject;
import com.ioc.model.User;
import com.ioc.service.CompanyService;
import com.ioc.service.UserService;

@Bean(name = "userServiceImpl")
public class UserServiceImpl implements UserService {

	@Inject(service = "userInstance", factory = "userInstance")
	public User user;

	@Inject(service = "companyServiceImpl")
	public CompanyService companyService;

	public CompanyService compService;


	public UserServiceImpl() {
	}

	

	public boolean createUser(String name) {
		User user = new User("Test");
		System.out.println("User created: " + name);
		return (user != null) ? true : false;
	}

	
	public void addUserToCompany() {
		//test @Inject annotation by method
		System.out.println("----------------- @Inject annotation by method -----------------");
		compService.addUser();
		//test @Inject by field
		System.out.println("----------------- @Inject by field ------------------------");
		companyService.addUser();
		
		//test @Inject by factory-method
		System.out.println("------------------- @Inject by factory-method------------");
		System.out.println("Default user Name: " + user.getName());
	}

	public CompanyService getCompanyServiceMethod() {
		return compService;
	}

	@Inject(service = "companyServiceImpl")
	public void setCompanyServiceMethod(CompanyService companyServiceMethod) {
		this.compService = companyServiceMethod;
	}

}
