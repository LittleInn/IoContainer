package com.ioc.logger;

public enum LogLevel {
    INFO(1), DEBUG(0), ERROR(2);
    int level;

    private LogLevel(int level) {
	this.level = level;
    }

    public int getLevel() {
	return level;
    }

    public void setLevel(int level) {
	this.level = level;
    }
}
