package com.ioc.usage;

import com.ioc.cache.CacheBuilder;
import com.ioc.composite.ComponentHierarchy;
import com.ioc.composite.SubComponent;
import com.ioc.context.ContextExcecutor;
import com.ioc.context.ContextExcecutorAdapter;
import com.ioc.impl.UserServiceImpl;
import com.ioc.model.SingletonServiceA;
import com.ioc.model.User;

public class IoCFacade {
    public IoCFacade() {
    }

    public void processClientRequest() {
	ContextExcecutor builder = new ContextExcecutorAdapter();
	builder.excecuteContext();

	testSingleton(builder);
	 testCache();
	testClassHierarchy();

    }

    private void testSingleton(ContextExcecutor builder) {
	SingletonServiceA singletonA1 = (SingletonServiceA) builder.getBean("SingletonServiceAImpl");
	System.out.println("Singleton Service A Bean: " + builder.getBean("SingletonServiceAImpl"));
	singletonA1.printInfo();
    }

    private void testCache() {
	CacheBuilder cacheBuilder = new CacheBuilder();
	for (int i = 0; i < 100; i++) {
	    User user = new User(i, "User #" + i);
	    cacheBuilder.add(i, user);
	}
	User user10 = (User) cacheBuilder.get(10);
	User user1990 = (User) cacheBuilder.get(1990);
	System.out.println("User #10: " + user10.getName());
//	System.out.println("User: #1990" + user1990.getName());
    }

    private void testClassHierarchy() {
	SubComponent userService = new SubComponent(UserServiceImpl.class, 0);
	ComponentHierarchy hierarchy = new ComponentHierarchy(userService);
	hierarchy.service();
	System.out.println(hierarchy.getHierarchy(userService));
    }
}
