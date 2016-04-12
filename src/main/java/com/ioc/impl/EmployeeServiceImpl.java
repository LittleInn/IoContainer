package com.ioc.impl;

import com.ioc.annotations.Bean;
import com.ioc.annotations.Inject;
import com.ioc.service.CompanyService;
import com.ioc.service.EmployeeService;

@Bean(name = "employeeServiceImpl")
public class EmployeeServiceImpl implements EmployeeService {

    public CompanyService companyService;

    public EmployeeServiceImpl() {
    }

    @Inject(service = "companyServiceImpl")
    public EmployeeServiceImpl(CompanyService companyService) {
	super();
	this.companyService = companyService;
    }

    @Override
    public void addEmployeeToCompany() {
	// test @Inject by constructor
	System.out.println("------------------ @Inject by constructor----------------");
	companyService.addUser();

    }

    public CompanyService getCompanyService() {
	return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
	this.companyService = companyService;
    }

}
