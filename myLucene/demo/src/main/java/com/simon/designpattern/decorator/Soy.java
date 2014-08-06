package com.simon.designpattern.decorator;

/**
 * @Author : simon
 * @version : Apr 22, 2014 8:44:12 PM
 *
 **/
public class Soy extends CondimentDecorator {

	Beverage beverage;

	public Soy(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDesc() {
		return beverage.getDesc() + ", Soy";
	}

	@Override
	public double cost() {
		return .15 + beverage.cost();
	}

}
