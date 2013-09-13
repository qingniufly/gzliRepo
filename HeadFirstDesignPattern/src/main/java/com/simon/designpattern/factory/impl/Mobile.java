package com.simon.designpattern.factory.impl;

import com.simon.designpattern.factory.Product;

/**
 * @author Simon
 *
 */
public class Mobile implements Product {

	private String color;

	private Integer price;

	public Mobile () {
		this.color = "default color";
		this.price = 0;
	}
	public Mobile(String color, Integer price) {
		this.color = color;
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public void desc() {
		System.out.println(this.toString());;
	}

	@Override
	public String toString() {
		return "Mobile [color=" + color + ", price=" + price + "]";
	}

}
