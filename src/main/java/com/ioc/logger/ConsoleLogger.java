package com.ioc.logger;

public class ConsoleLogger extends Logger {

    public ConsoleLogger(LogComponent log) {
	super(log);
    }

    public ConsoleLogger(LogComponent log, LogLevel level) {
	super(log, level);
    }

    @Override
    public String info(String log) {
	String logMsg = super.info(log);
	System.out.println(logMsg + log);
	return logMsg + log;
    }

    @Override
    public String error(String log) {
	String errorMsg = super.error(log);
	System.out.println(errorMsg + log);
	return errorMsg + log;
    }

    @Override
    public String log(String log) {
	String logMsg = super.info(log);
	System.out.println(logMsg + log);
	return logMsg + log;
    }

}
