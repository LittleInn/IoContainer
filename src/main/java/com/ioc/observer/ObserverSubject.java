package com.ioc.observer;

public interface ObserverSubject {
	void attach(BeanObserver o);
	void detach(BeanObserver o);
	void notifyBeanObserver();
}
