package com.proxy.introduction;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

/**
 * Greeting增强类，用Component注入到spring容器中
 * 该类实现了Apology接口，并重写saySorry方法，来作为增强方法
 */
@Component
public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor implements Apology{

	private static final long serialVersionUID = 1L;

	public Object invoke(MethodInvocation invocation) throws Throwable{
		return super.invoke(invocation);
	}
	
	@Override
	public void saySorry(String name) {
		System.out.println(name+"say sorry!");
	}

}
