package com.ioc.transaction;

public class TransactionStateProcessor {
    private TransactionState state;

    public TransactionState getState() {
	return state;
    }

    public void setState(TransactionState state) {
	this.state = state;
    }

    public TransactionMemento saveState(TransactionState state, Object object) {
	return new TransactionMemento(state, object);
    }

    public Object restoreState(TransactionMemento memento) {
	this.state = memento.getState();
	return memento.getObject();
    }
}
