package org.fornever.reflectfeature;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.fornever.reflectfeature.annotations.RequestMapping;
import org.fornever.reflectfeature.proxies.MockORMDynamicProxy;

public class ReflectUtil {

	public static List<String> getFieldsNames(Object o) {
		List<String> rt = new ArrayList<>();
		for (Field f : o.getClass().getDeclaredFields()) {
			rt.add(f.getName());
		}
		return rt;
	}

	public static List<Object> getFieldsValues(Object o) throws IllegalArgumentException, IllegalAccessException {
		List<Object> rt = new ArrayList<>();
		for (Field f : o.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			rt.add(f.get(o));
		}
		return rt;
	}

	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	public static <T> T newInstanceFor(Class<T> type, Object... params)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		T rt = null;
		Integer paramsLen = params.length;
		if (params == null) {
			rt = type.newInstance();
		} else {
			for (Constructor c : type.getConstructors()) {
				if (c.getParameterTypes().length == paramsLen) {
					rt = (T) c.newInstance(params);
					break;
				}
			}
		}

		return rt;
	}

	public static RequestMapping getMappingInformationFromMethod(Method m) {
		return m.getDeclaredAnnotation(RequestMapping.class);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getRepositoryProxy(Class<T> type) {
		return (T) Proxy.newProxyInstance(ReflectUtil.class.getClassLoader(), new Class[] { type },
				new MockORMDynamicProxy());
	}

}
