package com.ioc.usage;


public class RunApp {
    public RunApp() {

    }

    public static void main(String[] args) {
	IoCFacade facade = new IoCFacade();
	facade.processClientRequest();
//	Logger log1 = new ConsoleLogger(new LevelAppender(new ClassAppender(new DateAppender(new Log()), RunApp.class)));
//	log1.info("Important log");
//	log1.error("wwException");

    }
}
