package com.simon.spring.cache;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CacheMain {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-cache-config.xml");
		AcctService service = context.getBean("acctService", AcctService.class);
		System.out.println("first query...");
		service.getAccountByName("somebody");
		System.out.println("second query...");
		service.getAccountByName("somebody");
		
		// 更新某个记录的缓存，首先构造两个账号记录，然后记录到缓存中
		System.out.println("start testing clear cache...");    
		Account acct1 = service.getAccountByName("somebody1");
		service.getAccountByName("somebody2");
		// 更新其中一个
		acct1.setId(1212);
		System.out.println("clear one record...");
		service.updateAccount(acct1);
		// 因为此条记录被更新了,应该查询数据库
		service.getAccountByName("somebody1");
		// 没有更新过，应该走缓存
		service.getAccountByName("somebody2");
		// 被缓存了,所以不会查询数据库
		service.getAccountByName("somebody1");
		// 清空cache
		System.out.println("clear all cache...");
		service.reload();
		// 因为缓存被清空了,所以应该查询数据库
		service.getAccountByName("somebody1");
		service.getAccountByName("somebody2");
		// 应该走缓存
		service.getAccountByName("somebody1");
		service.getAccountByName("somebody2");
		
		System.out.println("query cache by condition...");
		service.getAccountByNameCondition("goodboy");
		service.getAccountByNameCondition("boy");
		service.getAccountByNameCondition("goodboy");
		service.getAccountByNameCondition("boy");
		
		System.out.println("query cache by multiple field key");
		service.getAccount("abc", "123456", true);
		service.getAccount("abc", "123456", false);
		service.getAccount("abc", "123456", true);
		service.getAccount("abc1", "23456", true);
		
		context.close();
		
		Account acctx = service.getAccountByName("o1");
		System.out.println("before update ... " + acctx);
		acctx.setPassword("asdfasdf");
		acctx.setId(22334);
		service.updateAccountAndReloadCache(acctx);
		Account newAcct = service.getAccountByName("o1");
		System.out.println("after update ..." + newAcct);
		
	}

}
