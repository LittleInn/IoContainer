package com.ioc.logger;

public abstract class Logger implements LogComponent {
    protected LogComponent component;

    private LogLevel level;
    protected Logger next;

    public Logger setNext(Logger next) {
	this.next = next;
	return next;
    }

    public Logger(LogComponent component) {
	super();
	this.component = component;
    }

    public Logger(LogComponent component, LogLevel level) {
	super();
	this.component = component;
	this.level = level;
    }

    public String info(String log) {
	return component.info(log);
    }

    public String error(String log) {
	return component.error(log);
    }

    public String log(String log) {
	return component.log(log);
    }

    public String logMessage(String log, LogLevel level) {
	if (level.level >= getLevel().level) {
	    log(log);
	}
	if (next != null) {
	    next.logMessage(log, level);
	}
	return component.log(log);
    }

    public LogLevel getLevel() {
	System.out.println("level: " + level);
	return level;
    }

    public void setLevel(LogLevel level) {
	this.level = level;
    }

    protected boolean checkPriority(int priority) {
	return (priority >= getLevel().level) ? true : false;
    }
}
