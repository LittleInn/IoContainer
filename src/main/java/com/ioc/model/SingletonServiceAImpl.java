package com.ioc.model;

import com.ioc.annotations.Inject;
import com.ioc.annotations.Singleton;
import com.ioc.impl.MailService;
import com.ioc.logger.ClassAppender;
import com.ioc.logger.ConsoleLogger;
import com.ioc.logger.LevelAppender;
import com.ioc.logger.Log;
import com.ioc.logger.Logger;
import com.ioc.service.CompanyService;

@Singleton
public class SingletonServiceAImpl implements SingletonServiceA {

    @Inject(name = "SingletonServiceBImpl")
    public SingletonServiceB singletonServiceB;
    private final static Logger LOG = new ConsoleLogger(new LevelAppender(new ClassAppender(new Log(), SingletonServiceAImpl.class)));

    @Inject(name = "companyServiceImpl")
    private CompanyService companyService;

    @Inject
    private MailService mailService;

    public SingletonServiceAImpl() {
    }

    @Inject(name = "SingletonServiceBImpl")
    public SingletonServiceAImpl(SingletonServiceB singletonServiceB) {
	LOG.info("SingletonServiceAImpl(singletonServiceB): " + singletonServiceB);
	this.singletonServiceB = singletonServiceB;
    }

    @Override
    public void printInfo() {
	LOG.info("In SingletonServiceAImpl printInfo() method");
	singletonServiceB.provideInfo();
	companyService.createCompany("MAIN Company");
	// mailService.printMail();
    }

}
