package com.proxy;

/**
 *	��̬�����࣬ÿһ�������������Ҫһ��Proxy���������д��� 
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
