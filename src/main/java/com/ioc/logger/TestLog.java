package com.ioc.logger;

public class TestLog {
    private static void test() {

	LogContext context = new LogContext();
	Logger logger = context.initLogger(true, LogLevel.INFO, true, LogLevel.INFO, new LevelAppender(new Log()));
	logger.logMessage("temp log msg", LogLevel.INFO);
    }

    public static void main(String[] args) {
	TestLog.test();
    }
}
