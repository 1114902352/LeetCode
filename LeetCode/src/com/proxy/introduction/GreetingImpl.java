package com.proxy.introduction;

import org.springframework.stereotype.Component;
/**
 *	该类只是实现了Greeting接口，而spring的引入增强可以在该类的字节码阶段再多实现一个Apology接口 
 */
@Component
public class GreetingImpl implements Greeting{

	@Override
	public void SayHi(String name) {
		System.out.println(name+" say Hi!");
	}
}
