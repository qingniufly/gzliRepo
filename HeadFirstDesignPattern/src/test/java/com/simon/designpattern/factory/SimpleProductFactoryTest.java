package com.simon.designpattern.factory;

import com.simon.designpattern.factory.impl.SimpleProductFactory;

public class SimpleProductFactoryTest {

	public static void main(String[] args) {
		Product prod = SimpleProductFactory.getOneProduct("car");
		prod.desc();
		prod = SimpleProductFactory.getOneProduct("mobile");
		prod.desc();
	}

}
