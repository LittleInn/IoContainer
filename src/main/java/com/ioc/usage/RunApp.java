package com.ioc.usage;

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
//	IoCFacade facade = new IoCFacade();
//	facade.processClientRequest();
//	FileLogger log = new  FileLogger();
//	Logger log1 = new  ConsoleLogger(new DateAppender( new LevelAppender( new Log())));
	Logger log1 = new  ConsoleLogger(new LevelAppender(new ProcessAppender(new DateAppender( new Log()))));
//	log.info("Logs are written");
	log1.info("Important log");
	log1.error("wwException");
//	log1.log("sd");
    }
}
