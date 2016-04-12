package com.ioc.model;

import com.ioc.annotations.Inject;
import com.ioc.annotations.Singleton;

@Singleton
public class SingletonServiceCImpl implements SingletonServiceC {
	
//	@Inject(service = "SingletonServiceAImpl")
	public SingletonServiceA singletonServiceA;

	public SingletonServiceCImpl() {
	}

	@Inject(service = "SingletonServiceAImpl")
	public SingletonServiceCImpl(SingletonServiceA singletonServiceA) {
		System.out.println("SingletonServiceCImpl(singletonServiceA): "+singletonServiceA);
		this.singletonServiceA = singletonServiceA;
	}

	@Override
	public void describe() {
		System.out.println("In describe method class SigletonServiceCImlp");
	}

}
