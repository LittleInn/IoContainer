package com.ioc.model;

import com.ioc.annotations.Bean;
import com.ioc.annotations.Provides;

@Bean(name = "user")
public class User implements BaseUser {
    private String name;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public User() {
    }

    public User(String name) {
	super();
	this.name = name;
    }

    @Provides(name = "userInstance")
    public User getInstance() {
	User user = new User();
	user.setName("USER");
	return user;
    }

}
