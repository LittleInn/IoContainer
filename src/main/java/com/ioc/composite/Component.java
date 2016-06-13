package com.ioc.composite;


public interface Component {
    void service();
    Class<?> getClazz();
    Integer level();
}
