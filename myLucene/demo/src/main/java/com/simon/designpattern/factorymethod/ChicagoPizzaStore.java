package com.simon.designpattern.factorymethod;

public class ChicagoPizzaStore extends PizzaStore {

	@Override
	public Pizza createPizza(String type) {
		Pizza pizza = null;
		if ("cheese".equalsIgnoreCase(type)) {
			pizza = new ChicagoStyleCheesePizza();
		} else if ("".equalsIgnoreCase(type)) {
			// create other flavor chicago pizza
		}
		return pizza;
	}

}
