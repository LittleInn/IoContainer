package com.ioc.transaction;

public class RollbackTransactionState implements State {
    private TransactionStateProcessor stateProcessor;

    public RollbackTransactionState(TransactionStateProcessor stateProcessor) {
	super();
	this.stateProcessor = stateProcessor;
    }

    public RollbackTransactionState() {
	this(new TransactionStateProcessor());
    }

    @Override
    public void handle(Object obj) {
	System.out.println("Object transaction failed");
	stateProcessor.saveState(TransactionState.FAILED, obj);
    }

}
