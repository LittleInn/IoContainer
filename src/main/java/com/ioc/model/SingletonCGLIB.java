package com.ioc.model;

import com.ioc.annotations.Inject;
import com.ioc.annotations.Singleton;
import com.ioc.service.CompanyService;

@Singleton
public class SingletonCGLIB {
    @Inject
    private CompanyService companyService;

    public void method() {
	System.out.println("=============================TEst CGLIB singleton");
	companyService.createCompany("********************************");
    }
}
