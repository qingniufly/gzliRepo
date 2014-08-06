package com.simon.designpattern.decorator;

/**
 * @Author : simon
 * @version : Apr 22, 2014 8:44:12 PM
 *
 **/
public class Whip extends CondimentDecorator {

	Beverage beverage;

	public Whip(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDesc() {
		return beverage.getDesc() + ", Whip";
	}

	@Override
	public double cost() {
		return .1 + beverage.cost();
	}

}
