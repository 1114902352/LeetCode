package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK��̬���� :ʵ��InvocationHandler�ӿڣ�����д invoke����
 * �ŵ�:�ӿڸı䣬�����಻��Ҫ����
 * ȱ��:�������������һ���ӿ�
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
	 * <T>������һ�����ͣ�T���Ǿ�������� 
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
