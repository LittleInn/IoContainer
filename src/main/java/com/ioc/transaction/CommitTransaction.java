package com.ioc.transaction;

public class CommitTransaction implements Transactions {
    private Transaction transactions;

    public CommitTransaction(Transaction transactions) {
	super();
	this.transactions = transactions;
    }
    public CommitTransaction() {
	this(new Transaction());
    }

    @Override
    public void execute() {
	transactions.commitAction();
    }
}
