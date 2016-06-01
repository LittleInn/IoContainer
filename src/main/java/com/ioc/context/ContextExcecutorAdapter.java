package com.ioc.context;

//Adapter
public class ContextExcecutorAdapter extends ContextExcecutorHelper implements ContextExcecutor     {

    @Override
    public Object getBean(String beanName) {
	System.out.println("~~~~~~~~~~~ Excecution thought Adapter getBean()~~~~~~~~~~~~~~~");
	return getBeanByName(beanName);
    }
    @Override
    public void scanBeanClasses() {
	System.out.println("~~~~~~~~~~~ Excecution thought Adapter  scanBeanClasses()~~~~~~~~~~~~~~~");
	scanBeansClasses();
    }
    @Override
    public void excecuteContext() {
	System.out.println("~~~~~~~~~~~ Excecution thought Adapter excecuteContext() ~~~~~~~~~~~~~~~");
	excecuteAppContext();
    }
}
