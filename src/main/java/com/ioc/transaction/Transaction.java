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
	System.out.println("Begin Action");
    }

    public void commitAction() {
	System.out.println("Commit Action");
    }

    public void revertAction() {
	System.out.println("Revert Action");
    }
}
