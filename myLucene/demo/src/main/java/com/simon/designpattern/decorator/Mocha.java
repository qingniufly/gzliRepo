package com.simon.designpattern.decorator;
/**
 * @Author  : simon
 * @version : Apr 22, 2014 8:42:01 PM
 *
 **/
public class Mocha extends CondimentDecorator {

	Beverage beverage;

	public Mocha(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDesc() {
		return beverage.getDesc() + ", Mocha";
	}

	@Override
	public double cost() {
		return .20 + beverage.cost();
	}

}
