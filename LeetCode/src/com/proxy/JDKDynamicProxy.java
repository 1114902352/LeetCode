package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理 :实现InvocationHandler接口，并重写 invoke方法
 * 优点:接口改变，代理类不需要更改
 * 缺点:被代理类必须有一个接口
 */
public class JDKDynamicProxy implements InvocationHandler{
	
	private Object target;
	
	public JDKDynamicProxy(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object result = method.invoke(target, args);
		after();
		return result;
	}
	
	private void before(){
		System.out.println("Jdk dynamic before advice");
	}

	private void after(){
		System.out.println("Jdk dynamic behind advice");
	}
	
	/**
	 * <T>声明是一个泛型，T才是具体的类型 
	 */
	@SuppressWarnings("unchecked")
	public <T> T getProxy(){
		return (T)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
	
	public static void main(String[] args) {
		JDKDynamicProxy dynamicProxy = new JDKDynamicProxy(new HelloImpl());
		Hello helloProxy = dynamicProxy.getProxy();
		helloProxy.say("Lance ");
	}
	
}
