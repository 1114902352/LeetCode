package com.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 *	CGLib��̬��������������asm,cglib����ע��hibernate��spring�ж�����cglib��̬�������������п�����ɰ���ͻ
 *	CGLib�ṩ�����Ƿ�������Ĵ���Ҳ�������Ϊ�Է��������أ����Ҵ�ʱ���������Ѿ�����Ҫ��ʵ��һ���ӿڡ�
 */
public class CGlibDynamicProxy implements MethodInterceptor{

	private static CGlibDynamicProxy instance = new CGlibDynamicProxy();
	
	private CGlibDynamicProxy() {}
	
	/**
	 *	����ģʽ�ṩ���� 
	 */
	public static CGlibDynamicProxy getInstance(){
		return instance;
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		before();
		Object result = proxy.invokeSuper(obj, args);
		after();
		return result;
	}
	
	private void before(){
		System.out.println("CGLib dynamic before advice");
	}

	private void after(){
		System.out.println("CGLib dynamic behind advice");
	}

	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<T> cls){
		return (T) Enhancer.create(cls, this);
	}
	
	public static void main(String[] args) {
		CGLibHello helloProxy = CGlibDynamicProxy.getInstance().getProxy(CGLibHello.class);
		helloProxy.say("Lance");
	}
}
