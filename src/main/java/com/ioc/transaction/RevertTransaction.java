package com.ioc.transaction;

public class RevertTransaction implements Transactions {
    private Transaction transactions;

    public RevertTransaction(Transaction transactions) {
	super();
	this.transactions = transactions;
    }
    public RevertTransaction() {
	this(new Transaction());
    }

    @Override
    public void execute() {
	transactions.revertAction();
    }
}
