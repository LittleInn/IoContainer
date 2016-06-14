package com.ioc.transaction;

public class RollbackTransaction implements Transactions {
    private Transaction transactions;

    public RollbackTransaction(Transaction transactions) {
	super();
	this.transactions = transactions;
    }
    public RollbackTransaction() {
	this(new Transaction());
    }

    @Override
    public void execute() {
	transactions.rollbackAction();
    }
}
