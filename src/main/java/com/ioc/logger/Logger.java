package com.ioc.logger;


public abstract class Logger implements LogComponent{
    protected LogComponent component;
    
    public Logger(LogComponent component) {
	super();
	this.component = component;
    }

    public String info(String log){
	return component.info(log);
    }

    public String error(String log){
	return component.error(log);
    }
}
