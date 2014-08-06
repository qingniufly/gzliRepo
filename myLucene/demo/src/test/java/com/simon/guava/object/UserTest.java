package com.simon.guava.object;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.joda.time.DateTime;
import org.joda.time.Years;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import com.simon.guava.User;

/**
 * @Author : simon
 * @version : Jun 20, 2014 11:17:37 AM
 *
 **/
public class UserTest {

	@Test
	public void testPrintUserIfno() {
		User u = new User();
		u.setUserId("007");
		u.setUsername("JB007");
		u.setRealName("James Bound");
		System.out.println(u);
	}

	@Test
	public void testSortUser() {
		List<User> users = new ArrayList<>();
		User u1 = new User();
		u1.setUserId("001");
		u1.setUsername("JB007");
		u1.setRealName("James Bound");
		u1.setBirthDate(DateTime.parse("1985-02-13"));
		u1.setRegisterDate(DateTime.parse("2010-10-07"));
		u1.setAge(Years.yearsBetween(u1.getBirthDate(), DateTime.now()).getYears());
		users.add(u1);

		User u2 = new User();
		u2.setUserId("002");
		u2.setUsername("JJ14");
		u2.setRealName("JJ Lin Junjie");
		u2.setBirthDate(DateTime.parse("1986-06-13"));
		u2.setRegisterDate(DateTime.parse("2010-06-06"));
		u2.setAge(Years.yearsBetween(u2.getBirthDate(), DateTime.now()).getYears());
		users.add(u2);

		User u3 = new User();
		u3.setUserId("003");
		u3.setUsername("Bill70");
		u3.setRealName("Bill Gates");
		u3.setBirthDate(DateTime.parse("1985-01-19"));
		u3.setRegisterDate(DateTime.parse("2008-06-06"));
		u3.setAge(Years.yearsBetween(u3.getBirthDate(), DateTime.now()).getYears());
		users.add(u3);

		for (User u : users) {
			System.out.println(u);
		}

		System.out.println("==============================");
		Collections.sort(users);

		for (User u : users) {
			System.out.println(u);
		}

	}

	private void testSet(Set<User> users) {
		checkNotNull(users);
		if (users.size() > 0) {
			users.clear();
		}
		User u1 = new User();
		u1.setUserId("001");
		u1.setUsername("JB007");
		u1.setRealName("James Bound");
		u1.setBirthDate(DateTime.parse("1985-02-13"));
		u1.setRegisterDate(DateTime.parse("2010-10-07"));
		u1.setAge(Years.yearsBetween(u1.getBirthDate(), DateTime.now()).getYears());
		users.add(u1);

		User u2 = new User();
		u2.setUserId("002");
		u2.setUsername("JJ14");
		u2.setRealName("JJ Lin Junjie");
		u2.setBirthDate(DateTime.parse("1986-06-13"));
		u2.setRegisterDate(DateTime.parse("2010-06-06"));
		u2.setAge(Years.yearsBetween(u2.getBirthDate(), DateTime.now()).getYears());
		users.add(u2);

		User u3 = new User();
		u3.setUserId("003");
		u3.setUsername("Bill70");
		u3.setRealName("Bill Gates");
		u3.setBirthDate(DateTime.parse("1985-01-19"));
		u3.setRegisterDate(DateTime.parse("2008-06-06"));
		u3.setAge(Years.yearsBetween(u3.getBirthDate(), DateTime.now()).getYears());
		users.add(u3);

		for (User u : users) {
			System.out.println(u);
		}
	}

	@Test
	public void testHashSet() {
		Set<User> users = new LinkedHashSet<>();
		testSet(users);
	}

	@Test
	public void testTreeSet() {
		Set<User> users = new TreeSet<>();
		testSet(users);
	}

	@Test
	public void testOrdering() {
		Set<User> users = new LinkedHashSet<>();
		User u1 = new User();
		u1.setUserId("001");
		u1.setUsername("JB007");
		u1.setRealName("James Bound");
		u1.setBirthDate(DateTime.parse("1985-02-13"));
		u1.setRegisterDate(DateTime.parse("2010-10-07"));
		u1.setAge(Years.yearsBetween(u1.getBirthDate(), DateTime.now()).getYears());
		users.add(u1);

		User u2 = new User();
		u2.setUserId("002");
		u2.setUsername("JJ14");
		u2.setRealName("JJ Lin Junjie");
		u2.setBirthDate(DateTime.parse("1986-06-13"));
		u2.setRegisterDate(DateTime.parse("2010-06-06"));
		u2.setAge(Years.yearsBetween(u2.getBirthDate(), DateTime.now()).getYears());
		users.add(u2);

		User u3 = new User();
		u3.setUserId("003");
		u3.setUsername("Bill70");
		u3.setRealName("Bill Gates");
		u3.setBirthDate(DateTime.parse("1985-01-19"));
		u3.setRegisterDate(DateTime.parse("2008-06-06"));
		u3.setAge(Years.yearsBetween(u3.getBirthDate(), DateTime.now()).getYears());
		users.add(u3);

		User u4 = new User();
		u4.setUserId("004");
		u4.setUsername("Robbin");
		u4.setRealName("Robbin Well");
		u4.setBirthDate(DateTime.parse("1980-12-30"));
		u4.setRegisterDate(DateTime.parse("2011-05-18"));
		users.add(u4);

		Function<User, Integer> func = new Function<User, Integer> () {
			@Override
			public Integer apply(User input) {
				return input.getAge();
			}
		};
		Ordering<User> ordering = Ordering.natural().nullsFirst().onResultOf(func);
		System.out.println(ordering.sortedCopy(users));

	}
}
