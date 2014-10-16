package com.simon.spring.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public class AcctService {

	@Cacheable(value = "acctCache")
	public Account getAccountByName(String userName) {
		// 方法内部实现不考虑缓存逻辑，直接实现业务
		System.out.println("real query account." + userName);
		return getFromDB(userName);
	}

	@Cacheable(value = "acctCache", condition = "#userName.length() <= 4")
	public Account getAccountByNameCondition(String userName) {
		// 方法内部实现不考虑缓存逻辑，直接实现业务
		System.out.println("real query account." + userName);
		return getFromDB(userName);
	}

	@CacheEvict(value = "acctCache", key = "#acct.getName()")
	public void updateAccount(Account acct) {
		updateDB(acct);
	}

	private Account updateDB(Account acct) {
		System.out.println("read update db..." + acct.getName());
		return acct;
	}

	@CacheEvict(value = "acctCache", allEntries = true)
	public void reload() {

	}

	private Account getFromDB(String acctName) {
		System.out.println("real querying db..." + acctName);
		return new Account(acctName);
	}

	@Cacheable(value = "acctCache", key="#userName.concat(\"~\" + #password)")
//	@Cacheable(value = "acctCache", key="#userName.concat(#password)")
	public Account getAccount(String userName, String password, boolean sendLog) {
		// 方法内部实现不考虑缓存逻辑，直接实现业务
		return getFromDB(userName, password);
	}

	private Account getFromDB(String userName, String password) {
		System.out.println("real querying db..." + userName);
		Account acct = new Account(userName);
		acct.setPassword(password);
		return acct;
	}
	
	@CachePut(value = "acctCache", key="#acct.getName()")
	public Account updateAccountAndReloadCache(Account acct) {
		return updateDB(acct);
	}

}
