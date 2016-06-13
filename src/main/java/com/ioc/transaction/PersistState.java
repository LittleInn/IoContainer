package com.ioc.transaction;

public class PersistState implements State {
    private TransactionStateProcessor stateProcessor;

    public PersistState(TransactionStateProcessor stateProcessor) {
	super();
	this.stateProcessor = stateProcessor;
    }
    public PersistState() {
	this(new TransactionStateProcessor());
    }

    @Override
    public void handle(Object obj) {
	System.out.println("Object transaction persisted");
	stateProcessor.saveState(TransactionState.DONE,obj);
    }

}
