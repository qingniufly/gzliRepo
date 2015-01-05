package com.simon.parameterizedtype;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ParameterizedTypeTest<T> {

	public ParameterizedTypeTest() {
	}
	
	public static void main(String[] args) {
		ParameterizedTypeTest<String> ptt = new ParameterizedTypeTest<String>() {
		};
		Type mySuperClass = ptt.getClass().getGenericSuperclass();
		Type type = ((ParameterizedType)mySuperClass).getActualTypeArguments()[0];
        System.out.println((Class<?>)type);
	}
	
}
