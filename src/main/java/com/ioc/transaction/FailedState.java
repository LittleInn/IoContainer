package com.ioc.transaction;

public class FailedState implements State {
    private TransactionStateProcessor stateProcessor;
    @Override
    public void handle(Object obj) {
	System.out.println("Object transaction failed");
	stateProcessor.saveState(TransactionState.FAILED,obj);
    }

}
