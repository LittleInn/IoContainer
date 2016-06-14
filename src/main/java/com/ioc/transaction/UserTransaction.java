package com.ioc.transaction;

public class UserTransaction {
    private Transactions transactionCommit;
    private Transactions transactionBegin;
    private Transactions transactionRollback;

    public UserTransaction() {
	this(new BeginTransaction(), new CommitTransaction(), new RollbackTransaction(), new TransactionStateProcessor());
    }

    public UserTransaction(BeginTransaction transactionBegin, CommitTransaction transactionCommit, RollbackTransaction transactionRevert, TransactionStateProcessor processor) {
	super();
	this.transactionBegin = transactionBegin;
	this.transactionCommit = transactionCommit;
	this.transactionRollback = transactionRevert;
    }

    public void commit() throws Exception {
	System.out.println("~~ Commit transaction ~~");
	transactionCommit.execute();

    }

    public void rollback() {
	System.out.println("~~ Rollback transaction ~~");
	transactionRollback.execute();
    }

    public void begin() {
	System.out.println("~~ Begin transaction ~~");
	transactionBegin.execute();
    }

}
