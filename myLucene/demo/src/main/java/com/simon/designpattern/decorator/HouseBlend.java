package com.simon.designpattern.decorator;
/**
 * @Author  : simon
 * @version : Apr 22, 2014 8:41:00 PM
 *
 **/
public class HouseBlend extends Beverage {

	public HouseBlend() {
		this.desc = "House Blend Coffee";
	}

	@Override
	public double cost() {
		return .89;
	}

}
