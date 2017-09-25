package com.proxy.fastclass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Date;

import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

public class Test {
	public static void main(String[] args) throws Exception {
		Class<People> peopleCla = People.class;
		Method method = peopleCla.getMethod("toString");
		Class[] parameterTypes = new Class[]{String.class,int.class,String.class};
		Object[] parameterValues = new Object[]{"张青",25,"男"};
		long s = (new Date()).getTime();
		FastClass fc = FastClass.create(peopleCla);
		Object fcTarget = fc.newInstance(parameterTypes, parameterValues);
		FastMethod fcMethod = fc.getMethod(method);
		Object result = fcMethod.invoke(fcTarget,null);
		System.out.println(result);
		long e = (new Date()).getTime();
		System.out.println("fastMethod："+(e-s));
		
		s = (new Date()).getTime();
		Constructor<People> con = peopleCla.getConstructor(parameterTypes);
		People rfTarget = con.newInstance(parameterValues);
		result = method.invoke(rfTarget);
		System.out.println(result);
		e = (new Date()).getTime();
		System.out.println("reflectMethod："+(e-s));
		
		for(int i=0;i<1000;i++){
			s = (new Date()).getTime();
			fc.getMethod(method);
			e = (new Date()).getTime();
			System.out.println("fastMethod："+(e-s));
			
			s = (new Date()).getTime();
			method.invoke(rfTarget);
			e = (new Date()).getTime();
			System.out.println("reflectMethod："+(e-s));
		}
	}
}
