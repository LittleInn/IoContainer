package com.ioc.model;

import com.ioc.annotations.Inject;
import com.ioc.annotations.Singleton;

@Singleton
public class SingletonServiceAImpl implements SingletonServiceA {

	// @Inject(service = "SingletonServiceBImpl")
	public SingletonServiceB singletonServiceB;

	public SingletonServiceAImpl() {
	}

	@Inject(service = "SingletonServiceBImpl")
	public SingletonServiceAImpl(SingletonServiceB singletonServiceB) {
		this.singletonServiceB = singletonServiceB;
	}

	 @Override
	public void printInfo() {
		System.out.println("In SingletonServiceAImpl printInfo() method");
		singletonServiceB.provideInfo();
	}

}
