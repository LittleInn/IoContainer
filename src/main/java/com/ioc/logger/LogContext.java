package com.ioc.logger;

public class LogContext {
    ConsoleLogger consoleLogger;
    FileLogger fileLogger;

    public Logger initLogger(boolean isConsoleLogger, LogLevel consoleLevel, boolean isFileLogger, LogLevel fileLevel, LogComponent logComponent) {
	if (isConsoleLogger) {

	    consoleLogger = new ConsoleLogger(logComponent, consoleLevel);
	}
	if (isFileLogger) {
	    fileLogger = new FileLogger(logComponent, fileLevel);
	}
	consoleLogger.setNext(fileLogger);
	return consoleLogger;
    }
}
