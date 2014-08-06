package com.simon.designpattern.decorator;
/**
 * @Author  : simon
 * @version : Apr 22, 2014 6:01:57 PM
 *
 **/
public abstract class Beverage {

	String desc;

	public Beverage() {
		this.desc = "Unknown Beverage";
	}

	public abstract double cost();

	public String getDesc() {
		return desc;
	}

	@Override
	public String toString() {
		return getDesc() + " $" + cost();
	}



}
