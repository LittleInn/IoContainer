package com.ioc.transaction;

public class CommitTransactiontState implements State {
    private TransactionStateProcessor stateProcessor;

    public CommitTransactiontState(TransactionStateProcessor stateProcessor) {
	super();
	this.stateProcessor = stateProcessor;
    }

    public CommitTransactiontState() {
	this(new TransactionStateProcessor());
    }

    @Override
    public void handle(Object obj) {
	System.out.println("Object transaction commited");
	stateProcessor.saveState(TransactionState.DONE, obj);
    }

}
