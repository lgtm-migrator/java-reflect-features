package org.fornever.reflectfeature;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.fornever.reflectfeature.annotations.RequestMapping;
import org.fornever.reflectfeature.controllers.HomeController;
import org.fornever.reflectfeature.models.User;
import org.fornever.reflectfeature.repos.UserRepository;

import junit.framework.TestCase;

public class ReflectUtilTest extends TestCase {

	private final static String userField1Name = "username";
	private final static String userField2Name = "age";
	private final static String testUsernameValue = "yourname";
	private final static Integer testUserAgeValue = 12;
	private final static User testUser = new User(testUsernameValue, testUserAgeValue);

	public void testGetFieldsNames() {
		List<String> fieldNames = ReflectUtil.getFieldsNames(testUser);
		assertEquals(fieldNames.size(), 2);
		assertEquals(fieldNames.get(0), userField1Name);
		assertEquals(fieldNames.get(1), userField2Name);
	}

	public void testFieldsValues() throws IllegalArgumentException, IllegalAccessException {
		List<Object> fieldValues = ReflectUtil.getFieldsValues(testUser);
		assertEquals(fieldValues.size(), 2);
		assertEquals(fieldValues.get(0), testUsernameValue);
		assertEquals(fieldValues.get(1), testUserAgeValue);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void testNewInstanceFor() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, ClassNotFoundException {
		Class userClass = Class.forName("org.fornever.reflectfeature.models.User");
		User u = (User) ReflectUtil.newInstanceFor(userClass, testUsernameValue, testUserAgeValue);
		assertEquals(u, testUser);
	}

	public void testGetRepositoryProxy() {
		UserRepository repo = ReflectUtil.getRepositoryProxy(UserRepository.class);
		assertEquals(null, repo.findUserByUsername("a username"));
	}

	public void testGetMappingInformationFromMethod() {
		for (Method method : HomeController.class.getDeclaredMethods()) {
			RequestMapping anno = ReflectUtil.getMappingInformationFromMethod(method);
			System.out.println(String.format("mapping %s %s", anno.method(), anno.path()));
		}
	}

}
