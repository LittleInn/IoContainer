package com.ioc.transaction;

public class CreatedState implements State{
    private TransactionStateProcessor stateProcessor;
    @Override
    public void handle(Object obj) {
	System.out.println("Object transaction created");
	stateProcessor.saveState(TransactionState.BEGIN,obj);
    }

}
