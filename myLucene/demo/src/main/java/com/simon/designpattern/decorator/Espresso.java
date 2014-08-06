package com.simon.designpattern.decorator;
/**
 * @Author  : simon
 * @version : Apr 22, 2014 8:37:46 PM
 *
 **/
public class Espresso extends Beverage {

	public Espresso() {
		this.desc = "Espresso";
	}

	@Override
	public double cost() {
		return 1.99;
	}

}
