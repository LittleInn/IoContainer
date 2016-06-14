package com.ioc.transaction;

public class BeginTransactionState implements State {
    private TransactionStateProcessor stateProcessor;

    public BeginTransactionState() {
	this(new TransactionStateProcessor());
    }

    public BeginTransactionState(TransactionStateProcessor stateProcessor) {
	super();
	this.stateProcessor = stateProcessor;
    }

    public TransactionStateProcessor getStateProcessor() {
	return stateProcessor;
    }

    public void setStateProcessor(TransactionStateProcessor stateProcessor) {
	this.stateProcessor = stateProcessor;
    }

    @Override
    public void handle(Object obj) {
	System.out.println("Object transaction begin");
	stateProcessor.saveState(TransactionState.BEGIN, obj);
    }

}
