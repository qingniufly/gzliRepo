package com.simon.designpattern.simplefactory;

import com.simon.designpattern.factorymethod.Pizza;

public class PizzaStore {

	private SimplePizzaFactory factory;

	public PizzaStore(SimplePizzaFactory factory) {
		this.factory = factory;
	}

	public Pizza orderPizza(String type) {
		Pizza pizza = null;
		pizza = factory.create(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}

}
