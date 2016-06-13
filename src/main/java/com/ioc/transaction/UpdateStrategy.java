package com.ioc.transaction;

public class UpdateStrategy implements Strategy{

    @Override
    public void execute(Object object) {
	System.out.println("Updated Object");
    }

}
