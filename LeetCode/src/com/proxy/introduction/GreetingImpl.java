package com.proxy.introduction;

import org.springframework.stereotype.Component;
/**
 *	����ֻ��ʵ����Greeting�ӿڣ���spring��������ǿ�����ڸ�����ֽ���׶��ٶ�ʵ��һ��Apology�ӿ� 
 */
@Component
public class GreetingImpl implements Greeting{

	@Override
	public void SayHi(String name) {
		System.out.println(name+" say Hi!");
	}
}
