package com.ioc.impl;

import com.ioc.annotations.Bean;

@Bean(name = "mailService")
public class MailService {
    
	public void printMail() {
		System.out.println("~~~~ Print mail ~~~~");
	}
}
