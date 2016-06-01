package com.ioc.composite;

public class SubComponent implements Component {
    private String property;

    @Override
    public void property() {
	System.out.println(property+" SubComponent");
    }

    public SubComponent(String property) {
	super();
	this.property = property;
    }

    public String getProperty() {
	return property;
    }

    public void setProperty(String property) {
	this.property = property;
    }

}
