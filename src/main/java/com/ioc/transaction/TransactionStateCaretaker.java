package com.ioc.transaction;

public class TransactionStateCaretaker {
    private TransactionMemento memento;

    public TransactionMemento getMemento() {
	return memento;
    }

    public void setMemento(TransactionMemento memento) {
	this.memento = memento;
    }

}
