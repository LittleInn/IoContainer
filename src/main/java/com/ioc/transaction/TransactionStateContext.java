package com.ioc.transaction;

public class TransactionStateContext {
    private State state;

    public TransactionStateContext(State state) {
	super();
	this.state = state;
    }

    public TransactionStateContext() {
	this(new BeginTransactionState());

    }

    public void request(Object obj) {
	state.handle(obj);
    }

    public State getState() {
	return state;
    }

    public void setState(State state) {
	this.state = state;
    }
}
