package com.ioc.transaction;

public class PersistStrategy implements Strategy{

    @Override
    public void execute(Object object) {
	System.out.println("Object Persisted ");
	
    }

}
