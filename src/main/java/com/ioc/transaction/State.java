package com.ioc.transaction;

public interface State {
    void handle(Object obj);
}
