package com.proxy.introduction;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

/**
 * Greeting��ǿ�࣬��Componentע�뵽spring������
 * ����ʵ����Apology�ӿڣ�����дsaySorry����������Ϊ��ǿ����
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
