package com.proxy;

/**
 *	静态代理类，每一个被代理对象都需要一个Proxy对象来进行代理 
 */
public class HelloStaticProxy implements Hello{

	private Hello hello;
	
	public HelloStaticProxy(){
		hello = new HelloImpl();
	}
	
	public void say(String name) {
		before();
		hello.say(name);
		after();
	}
	
	private void before(){
		System.out.println("before advice");
	}

	private void after(){
		System.out.println("behind advice");
	}
	
	public static void main(String[] args) {
		Hello helloProxy = new HelloStaticProxy();
		helloProxy.say("Lance ");		
	}
}
