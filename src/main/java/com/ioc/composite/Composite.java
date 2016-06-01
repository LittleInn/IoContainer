package com.ioc.composite;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {
    List<Component> components = new ArrayList<Component>();

    @Override
    public void property() {
	for (Component component : components) {
	    component.property();
	}
    }

    public void addComponent(Component component) {
	components.add(component);
    }
}
