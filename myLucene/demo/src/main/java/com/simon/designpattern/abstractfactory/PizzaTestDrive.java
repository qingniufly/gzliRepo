package com.simon.designpattern.abstractfactory;

public class PizzaTestDrive {

	public static void main(String[] args) {
		PizzaStore pizzaStore = new NYPizzaStore();
		pizzaStore.orderPizza("cheese");
	}

}
