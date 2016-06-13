package com.ioc.transaction;

public class TransactionMemento {
    private final TransactionState state;
    private final Object object;

    public TransactionMemento(TransactionState state, Object object) {
	super();
	this.state = state;
	this.object = object;
    }

    public TransactionState getState() {
	return state;
    }

    public Object getObject() {
	return object;
    }

}
