package com.ioc.logger;

public class ClassAppender extends Logger {
    private Class<?> clazz;

    public ClassAppender(LogComponent component) {
	super(component);
    }

    public ClassAppender(LogComponent component, Class<?> clazz) {
	super(component);
	this.clazz = clazz;
    }

    @Override
    public String info(String log) {
	return "\"" + clazz.getSimpleName() + ".class\": " + super.info(log);
    }

    @Override
    public String error(String log) {
	return "\"" + clazz.getSimpleName() + ".class\": " + super.error(log);
    }

    @Override
    public String log(String log) {
	return "\"" + clazz.getSimpleName() + ".class\": " + super.log(log);
    }
}
