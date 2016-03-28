package com.ioc.model;

import com.ioc.annotations.Inject;
import com.ioc.annotations.Singleton;

@Singleton
public class SingletonServiceCImpl implements SingletonServiceC {
	
	public SingletonServiceA singletonServiceA;

	public SingletonServiceCImpl() {
	}

	@Inject(service = "SingletonServiceAImpl")
	public SingletonServiceCImpl(SingletonServiceA singletonServiceA) {
		this.singletonServiceA = singletonServiceA;
	}

	@Override
	public void describe() {
		System.out.println("In describe method class SigletonServiceCImlp");
	}

}
