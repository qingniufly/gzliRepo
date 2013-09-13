package com.simon.designpattern.factory.impl;

import com.simon.designpattern.factory.Product;

/**
 * @author Simon
 *
 */
public class Car implements Product{

	private Integer speed;

	private Integer price;

	private String color;

	private String brand;

	public Car () {
		this.speed = 150;
		this.price = 25;
		this.color = "green";
		this.brand = "Ferrari";
	}

	public Car(String brand, String color, Integer price, Integer speed) {
		this.brand = brand;
		this.color = color;
		this.price = price;
		this.speed = speed;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public void desc() {
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return "Car [speed=" + speed + ", price=" + price + ", color=" + color
				+ ", brand=" + brand + "]";
	}

}
