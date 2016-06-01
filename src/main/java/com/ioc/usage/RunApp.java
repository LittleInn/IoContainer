package com.ioc.usage;

import com.ioc.logger.ClassAppender;
import com.ioc.logger.ConsoleLogger;
import com.ioc.logger.DateAppender;
import com.ioc.logger.LevelAppender;
import com.ioc.logger.Log;
import com.ioc.logger.Logger;

public class RunApp {
    public RunApp() {

    }

    public static void main(String[] args) {
	IoCFacade facade = new IoCFacade();
	facade.processClientRequest();
	Logger log1 = new ConsoleLogger(new LevelAppender(new ClassAppender(new DateAppender(new Log()), RunApp.class)));
	log1.info("Important log");
	log1.error("wwException");

    }
}
