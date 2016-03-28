package com.ioc.model;

import com.ioc.annotations.Inject;
import com.ioc.annotations.Singleton;

@Singleton
public class SingletonServiceBImpl implements SingletonServiceB {

	public SingletonServiceC singletonServiceC;

	public SingletonServiceBImpl() {
	}

	@Inject(service = "SingletonServiceCImpl")
	public SingletonServiceBImpl(SingletonServiceC singletonServiceC) {
		this.singletonServiceC = singletonServiceC;
	}

	@Override
	public void provideInfo() {
		System.out.println("In SingletonServiceBImpl provideInfo() method");
		//singletonServiceC.describe();
	}

}
