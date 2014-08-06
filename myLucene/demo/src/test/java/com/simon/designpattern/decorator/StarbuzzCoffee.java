package com.simon.designpattern.decorator;

import org.junit.Before;
import org.junit.Test;

/**
 * @Author  : simon
 * @version : Apr 22, 2014 8:48:08 PM
 *
 **/
public class StarbuzzCoffee {

	@Before
	public void setUp() {

	}

	@Test
	public void test() {
		Beverage beverage = new HouseBlend();
		System.out.println(beverage);

		beverage = new Soy(beverage);
		beverage = new Mocha(beverage);
		beverage = new Whip(beverage);
		System.out.println(beverage);
	}

}
