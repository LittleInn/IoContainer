package com.ioc.model;

import com.ioc.annotations.Inject;
import com.ioc.annotations.Singleton;

@Singleton
public class SingletonServiceBImpl implements SingletonServiceB {

	@Inject(name = "SingletonServiceCImpl")
	public SingletonServiceC singletonServiceC;

	public SingletonServiceBImpl() {
	}

	@Inject(name = "SingletonServiceCImpl")
	public SingletonServiceBImpl(SingletonServiceC singletonServiceC) {
		System.out.println("SingletonServiceBImpl(singletonServiceC): "+singletonServiceC);
		this.singletonServiceC = singletonServiceC;
	}

	@Override
	public void provideInfo() {
		System.out.println("In SingletonServiceBImpl provideInfo() method "+singletonServiceC);
		singletonServiceC.describe();
	}

}
