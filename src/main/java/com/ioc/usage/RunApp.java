package com.ioc.usage;

import com.ioc.cache.CacheBuilder;
import com.ioc.model.User;
import com.ioc.structural.ConsoleLogger;
import com.ioc.structural.DateAppender;
import com.ioc.structural.LevelAppender;
import com.ioc.structural.Log;
import com.ioc.structural.Logger;
import com.ioc.structural.ProcessAppender;

public class RunApp {
    public RunApp() {

    }

    public static void main(String[] args) {
	// IoCFacade facade = new IoCFacade();
	// facade.processClientRequest();
	// FileLogger log = new FileLogger();
	// Logger log1 = new ConsoleLogger(new DateAppender( new LevelAppender(
	// new Log())));
	Logger log1 = new ConsoleLogger(new LevelAppender(new ProcessAppender(new DateAppender(new Log()))));
	log1.info("Important log");
	log1.error("wwException");
	CacheBuilder cacheBuilder = new CacheBuilder();
	for (int i = 0; i < 100; i++) {
	    User user = new User(i, "User #" + i);
	    cacheBuilder.add(i, user);
	}
	User user = (User) cacheBuilder.get(1990);
	System.out.println("User: " + user.getName());
    }
}
