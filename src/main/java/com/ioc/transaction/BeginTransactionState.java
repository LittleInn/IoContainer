package com.ioc.transaction;

public class BeginTransactionState implements State {
    private TransactionStateProcessor stateProcessor;
    @Override
    public void handle(Object obj) {
	System.out.println("Object transaction begin");
	stateProcessor.saveState(TransactionState.BEGIN,obj);
    }

}
