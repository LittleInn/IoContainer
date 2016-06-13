package com.ioc.transaction;

public class EntityManagerStrategy {
    private PersistStrategy persistStrategy;
    private UpdateStrategy updateStrategy;
    private Object object;

    TransactionStateContext transactionStateContext = new TransactionStateContext();
    
    private static final EntityManagerStrategy CONTEXT = new EntityManagerStrategy();

    public EntityManagerStrategy() {
	this(new PersistStrategy(), new UpdateStrategy());
    }

    public EntityManagerStrategy(PersistStrategy persistStrategy, UpdateStrategy updateStrategy) {
	super();
	this.persistStrategy = persistStrategy;
	this.updateStrategy = updateStrategy;
    }

    public static EntityManagerStrategy getInstance() {
	return CONTEXT;
    }

    public void persist(Object object) throws Exception{
	setObject(object);
	transactionStateContext.setState(new PersistState());
	transactionStateContext.request(object);
	if(object == null)throw new Exception("Fail");
	
	persistStrategy.execute(object);
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

    public TransactionStateContext getTransactionStateContext() {
        return transactionStateContext;
    }

    public void setTransactionStateContext(TransactionStateContext transactionStateContext) {
        this.transactionStateContext = transactionStateContext;
    }
    
}
