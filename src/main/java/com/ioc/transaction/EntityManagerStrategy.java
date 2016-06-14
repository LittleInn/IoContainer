package com.ioc.transaction;

public class EntityManagerStrategy {
    private PersistStrategy persistStrategy;
    private UpdateStrategy updateStrategy;
    private Object object;
    private TransactionMemento memento;

    private static final EntityManagerStrategy CONTEXT = new EntityManagerStrategy();

    public EntityManagerStrategy() {
	this(new PersistStrategy(), new UpdateStrategy());
	this.transactionStateContext = new TransactionStateContext();
	this.transactionStateProcessor = new TransactionStateProcessor();
    }

    public EntityManagerStrategy(PersistStrategy persistStrategy, UpdateStrategy updateStrategy) {
	super();
	this.persistStrategy = persistStrategy;
	this.updateStrategy = updateStrategy;
    }

    public static EntityManagerStrategy getInstance() {
	return CONTEXT;
    }

    public void persist(Object object) throws Exception {
	setObject(object);
	if (object == null) {
	    transactionStateContext.setState(new RollbackTransactionState());
	    TransactionMemento saveState = transactionStateProcessor.saveState(TransactionState.FAILED, object);
	    setMemento(saveState);
	    throw new Exception("Fail");
	}

	persistStrategy.execute(object);
	transactionStateContext.setState(new CommitTransactiontState());
    }

    public void update(Object object) {
	setObject(object);
	updateStrategy.execute(object);
    }

    public Object getObject() {
	return object;
    }

    public void setObject(Object object) {
	this.object = object;
    }

    private TransactionStateContext transactionStateContext;
    private TransactionStateProcessor transactionStateProcessor;

    public TransactionStateContext getTransactionStateContext() {
	return transactionStateContext;
    }

    public void setTransactionStateContext(TransactionStateContext transactionStateContext) {
	this.transactionStateContext = transactionStateContext;
    }

    public TransactionStateProcessor getTransactionStateProcessor() {
	return transactionStateProcessor;
    }

    public void setTransactionStateProcessor(TransactionStateProcessor transactionStateProcessor) {
	this.transactionStateProcessor = transactionStateProcessor;
    }

    public TransactionMemento getMemento() {
	return memento;
    }

    public void setMemento(TransactionMemento memento) {
	this.memento = memento;
    }

}
