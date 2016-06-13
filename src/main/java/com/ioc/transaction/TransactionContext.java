package com.ioc.transaction;


public class TransactionContext {
   
    private EntityManagerStrategy context;

    public TransactionContext() {
	this.context = EntityManagerStrategy.getInstance();
    }

   

    public EntityManagerStrategy getContext() {
	return context;
    }

    public void setContext(EntityManagerStrategy context) {
	this.context = context;
    }

}
