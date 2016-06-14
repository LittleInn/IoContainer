package com.ioc.transaction;

public class Transaction {
    private TransactionStateContext transactionStateContext;

    public Transaction() {
	this(new TransactionStateContext());
    }

    public Transaction(TransactionStateContext transactionStateContext) {
	super();
	this.transactionStateContext = transactionStateContext;
    }

    public void beginAction() {
	transactionStateContext.setState(new BeginTransactionState());
    }

    public void commitAction() {
	transactionStateContext.setState(new CommitTransactiontState());
    }

    public void rollbackAction() {
	transactionStateContext.setState(new RollbackTransactionState());
    }
}
