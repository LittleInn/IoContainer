package com.ioc.composite;

import java.util.ArrayList;
import java.util.List;

public class SubComponent implements Component {
    private Integer level;
    private Class clazz;
    List<Component> componentsAll = new ArrayList<Component>();

    @Override
    public void service() {
	for(int i=0;i<getLevel();i++){
	    System.out.print("...");
	}
	System.out.println(" > "+getClazz().getSimpleName());
    }

    
    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }

    public SubComponent(Class clazz, Integer level) {
	super();
	this.clazz = clazz;
	this.level=level;
    }

    public Class getClazz() {
	return clazz;
    }

    public void setClazz(Class clazz) {
	this.clazz = clazz;
    }


//    @Override
//    public Class<?> getClazz() {
//	return getClazz();
//    }



    @Override
    public Integer level() {
	return getLevel();
    }

    @Override
    public String toString() {
	return "SubComponent [ level=" + level + ", clazz=" + clazz + "]";
    }
  
}
