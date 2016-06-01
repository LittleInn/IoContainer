package com.ioc.context;

//Target
public interface ContextExcecutor {
    Object getBean(String beanName);

    void scanBeanClasses();

    void excecuteContext();

}
