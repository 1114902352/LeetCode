package com.proxy;

public class HelloImpl implements Hello{

	@Override
	public void say(String name) {
		System.out.println(name+"say Hello!");
	}
}
