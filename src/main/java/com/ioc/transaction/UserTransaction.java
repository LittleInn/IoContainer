package com.ioc.transaction;

public class UserTransaction {
    private Transactions transactionCommit;
    private Transactions transactionRevert;
//    private TransactionContext context;
//    TransactionStateContext transactionStateContext = new TransactionStateContext();
    public UserTransaction(TransactionContext context) {
	this(new CommitTransaction(), new RevertTransaction(), new TransactionStateProcessor());
	//this.context = context;
    }

    public UserTransaction(CommitTransaction transactionCommit, RevertTransaction transactionRevert, TransactionStateProcessor processor) {
	super();
	this.transactionCommit = transactionCommit;
	this.transactionRevert = transactionRevert;
    }

    public void commit() throws Exception{
	transactionCommit.execute();
	
    }

    public void revert() {
	transactionRevert.execute();
    }

//    public TransactionContext getContext() {
//	return context;
//    }
//
//    public void setContext(TransactionContext context) {
//	this.context = context;
//    }

}
