package com.simon.spring.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

public class MyCache implements Cache {

	private String name;
	
	private Map<String, Account> store = new HashMap<>();
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Object getNativeCache() {
		return store;
	}

	@Override
	public ValueWrapper get(Object key) {
		ValueWrapper result = null;
		Account theValue = store.get(key);
		if (theValue != null) {
			theValue.setPassword("from mycache:" + name);
			result = new SimpleValueWrapper(theValue);
		}
		return result;
	}

	@Override
	public void put(Object key, Object value) {
		Account theValue = (Account) value;
		store.put((String)key, theValue);

	}

	@Override
	public void evict(Object key) {
		store.remove((String)key);
	}

	@Override
	public void clear() {
		store.clear();
	}

}
