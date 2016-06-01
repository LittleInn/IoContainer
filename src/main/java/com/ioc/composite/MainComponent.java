package com.ioc.composite;

public class MainComponent {
public static void main(String[] args) {
    SubComponent app = new SubComponent("app");
    SubComponent env = new SubComponent("env");
    SubComponent sys = new SubComponent("sys");
    Composite composite = new Composite();
    composite.addComponent(env);
    composite.addComponent(app);
    composite.addComponent(sys);
    sys.property();
    System.out.println("-----");
    composite.property();
}
}
