package com.simon.designpattern.factorymethod;

public class NYPizzaStore extends PizzaStore {

	@Override
	public Pizza createPizza(String type) {
		Pizza pizza = null;
		if ("cheese".equalsIgnoreCase(type)) {
			pizza = new NYStyleCheesePizza();
		} else if ("veggie".equalsIgnoreCase(type)) {
			pizza = new NYStyleVeggiePizza();
		} else if ("clam".equalsIgnoreCase(type)) {
			pizza = new NYStyleClamPizza();
		} else if ("pepperoni".equalsIgnoreCase(type)) {
			pizza = new NYStylePepperoniPizza();
		}
		return pizza;
	}

}
