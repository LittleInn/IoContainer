package com.ioc.transaction;

public class CreatedState implements State {
    private TransactionStateProcessor stateProcessor;

    public CreatedState() {
	super();
	// this.stateProcessor = stateProcessor;
    }

    @Override
    public void handle(Object obj) {
	System.out.println("Object transaction created");
	// stateProcessor.saveState(TransactionState.BEGIN, obj);
    }

    public TransactionStateProcessor getStateProcessor() {
	return stateProcessor;
    }

    public void setStateProcessor(TransactionStateProcessor stateProcessor) {
	this.stateProcessor = stateProcessor;
    }

}
