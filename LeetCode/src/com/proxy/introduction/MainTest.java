package com.proxy.introduction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/config/spring.xml");
		GreetingImpl greetingImpl = (GreetingImpl)context.getBean("greetingProxy");
		greetingImpl.SayHi("Lance ");
		Apology apology = (Apology)greetingImpl;
		apology.saySorry("Lance ");
	}
}
