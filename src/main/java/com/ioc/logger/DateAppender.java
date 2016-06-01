
package com.ioc.logger;

import java.util.Date;

public class DateAppender extends Logger{

    public DateAppender(LogComponent component) {
	super(component);
    }

    @Override
    public String info(String log) {
	return new Date()+": "+super.info(log);
    }

    @Override
    public String error(String log) {
	return new Date()+": "+super.error(log);
    }

}
