package org.fornever.reflectfeature.proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class MockORMDynamicProxy implements InvocationHandler {

	@Override
	public Object invoke(Object obj, Method method, Object[] params) throws Throwable {
		System.out.println(String.format("Invoked method %s with param %s", method.getName(), Arrays.toString(params)));
		return null;
	}

}
