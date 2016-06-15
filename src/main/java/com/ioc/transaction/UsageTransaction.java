package com.ioc.transaction;

import com.ioc.model.User;

public class UsageTransaction {
    private static void test() {
	User user = new User(1, "Inn");

	EntityManagerStrategy entityManager = EntityManagerStrategy.getInstance();
	UserTransaction transaction = new UserTransaction();

	try {
	    transaction.begin();
	    entityManager.persist(user);
	    System.out.println("Object saved by Memento: "+entityManager.getTransactionStateProcessor().restoreState(entityManager.getMemento()));
	    transaction.commit();
	} catch (Exception e) {
	    entityManager.getTransactionStateContext().request(user);
	    System.out.println("Object saved by Memento: "+entityManager.getTransactionStateProcessor().restoreState(entityManager.getMemento()));
	    transaction.rollback();
	}
    }

    public static void main(String[] args) {
	UsageTransaction.test();
    }
}
