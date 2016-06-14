package com.ioc.logger;

public class LevelAppender extends Logger {

    public LevelAppender(LogComponent component) {
	super(component);
    }

    @Override
    public String info(String log) {
	return "[INFO]: " + super.info(log);
    }

    @Override
    public String error(String log) {
	return "[ERROR]: " + super.error(log);
    }

    @Override
    public String log(String log) {
	return null;
    }
}
