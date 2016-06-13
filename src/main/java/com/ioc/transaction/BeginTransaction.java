package com.ioc.transaction;

public class BeginTransaction implements Transactions {
    private Transaction transactions;

    public BeginTransaction(Transaction transactions) {
	super();
	this.transactions = transactions;
    }
    public BeginTransaction() {
	this(new Transaction());
    }
    @Override
    public void execute() {
	transactions.beginAction();
	
    }
}
