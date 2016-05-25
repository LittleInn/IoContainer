package com.ioc.structural;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FileLogger extends Logger {
    public FileLogger(LogComponent component) {
	super(component);
	// TODO Auto-generated constructor stub
    }

    @Override
    public String info(String log) {
	try (RandomAccessFile file = new RandomAccessFile("D:\\IoC\\logs.log.txt", "rw")) {
	    file.writeBytes(log);
	} catch (IOException e) {
	    e.printStackTrace();
	}
return null;
    }

    @Override
    public String error(String log) {
	// TODO Auto-generated method stub
	return null;
    }

}
