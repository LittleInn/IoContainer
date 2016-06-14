package com.ioc.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger extends Logger {

    public FileLogger(LogComponent component) {
	super(component);
    }

    public FileLogger(LogComponent log, LogLevel level) {
	super(log, level);
    }

    @Override
    public String info(String log) {
	String logMsg = super.info(log);
	writelog(logMsg + log);
	return logMsg + log;
    }

    @Override
    public String error(String log) {
	String errorMsg = super.error(log);
	writelog(errorMsg + log);
	return errorMsg + log;
    }

    private void writelog(String log) {
	try {

	    File file = new File("D:\\log\\log.txt");

	    if (!file.exists()) {
		file.createNewFile();
	    }

	    FileWriter fw = new FileWriter(file.getAbsoluteFile());
	    BufferedWriter bw = new BufferedWriter(fw);
	    bw.write(log);
	    bw.close();

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public String log(String log) {
	String logMsg = super.info(log);
	writelog(logMsg + log);
	return logMsg + log;
    }
}
