package com.ioc.logger;

public class ProcessAppender extends Logger{

    public ProcessAppender(LogComponent component) {
	super(component);
    }
    @Override
    public String info(String log) {
	return "Thread - "+Thread.currentThread().getName()+": "+super.info(log);
    }

    @Override
    public String error(String log) {
	return "Thread - "+Thread.currentThread().getName()+": "+super.error(log);
    }
}
