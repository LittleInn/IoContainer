package com.ioc.transaction;

import com.ioc.model.User;

public class UsageTransaction {
    private static void test() {
	User user = new User(1,"Inn");
	TransactionContext context =new  TransactionContext();
	
	EntityManagerStrategy entityManager = context.getContext();
	UserTransaction transaction = new UserTransaction(context);
	try {
	    entityManager.persist(user);
	    entityManager.getTransactionStateContext().getState();
	    transaction.commit();
	} catch (Exception e) {
	    e.printStackTrace();
	    transaction.revert();
	}
    }

    public static void main(String[] args) {
	UsageTransaction.test();
    }
}
