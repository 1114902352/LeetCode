package com.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 *	CGLib动态代理：引入两个包asm,cglib，请注意hibernate和spring中都是用cglib动态代理，这两个很有可能造成包冲突
 *	CGLib提供给我们方法级别的代理，也可以理解为对方法的拦截，而且此时被代理类已经不需要再实现一个接口。
 */
public class CGlibDynamicProxy implements MethodInterceptor{

	private static CGlibDynamicProxy instance = new CGlibDynamicProxy();
	
	private CGlibDynamicProxy() {}
	
	/**
	 *	单例模式提供代理 
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
