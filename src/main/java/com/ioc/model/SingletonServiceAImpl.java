package com.ioc.model;

import com.ioc.annotations.Inject;
import com.ioc.annotations.Singleton;
import com.ioc.service.CompanyService;

@Singleton
public class SingletonServiceAImpl implements SingletonServiceA {

    public SingletonServiceB singletonServiceB;

    @Inject(service = "companyServiceImpl")
    public CompanyService companyService;

    public SingletonServiceAImpl() {
    }

    @Inject(service = "SingletonServiceBImpl")
    public SingletonServiceAImpl(SingletonServiceB singletonServiceB) {
	System.out.println("SingletonServiceAImpl(singletonServiceB): " + singletonServiceB);
	this.singletonServiceB = singletonServiceB;
    }

    @Override
    public void printInfo() {
	System.out.println("In SingletonServiceAImpl printInfo() method");
	System.out.println("Included class object " + singletonServiceB);
	singletonServiceB.provideInfo();
	companyService.createCompany("Create company in Singleton");
    }

}
