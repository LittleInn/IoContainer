package com.ioc.composite;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.ioc.annotations.Inject;

public class ComponentHierarchy implements Component {
    List<Component> componentsAll = new ArrayList<Component>();
    private Component component;

    @Override
    public void service() {
	buildHierarchy(component);
    }

    public ComponentHierarchy(Component component) {
	super();
	this.component = component;
    }

    public List<Component> getHierarchy(Component component) {
	return componentsAll;
    }

    @Override
    public Class<?> getClazz() {
	return null;
    }

    private void buildHierarchy(Component component) {
	Field[] fields = component.getClazz().getDeclaredFields();
	for (Field field : fields) {
	    if (field.isAnnotationPresent(Inject.class)) {
		int newlevel = component.level() + 1;
		SubComponent subComponent = new SubComponent(field.getType(), newlevel);
		componentsAll.add(subComponent);
		subComponent.service();
		buildHierarchy(subComponent);
	    }
	}
    }

    @Override
    public Integer level() {
	return 0;
    }
}
