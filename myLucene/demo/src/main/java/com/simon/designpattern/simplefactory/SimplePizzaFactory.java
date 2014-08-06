package com.simon.designpattern.simplefactory;

import com.simon.designpattern.factorymethod.Pizza;

public class SimplePizzaFactory {

	public Pizza create(String type) {
		Pizza pizza = null;
		if ("cheese".equalsIgnoreCase(type)) {
			pizza = new CheesePizza();
		} else if ("pepperoni".equalsIgnoreCase(type)) {
			pizza = new PepperoniPizza();
		} else if ("clam".equalsIgnoreCase(type)) {
			pizza = new ClamPizza();
		} else if ("veggie".equalsIgnoreCase(type)) {
			pizza = new VeggiePizza();
		}
		return pizza;
	}

}
