package com.simon.designpattern.abstractfactory;

import java.util.Arrays;


public abstract class Pizza {

	// 名称
	String name;
	// 面团
	Dough dough;
	// 酱料
	Sauce sauce;
	// 佐料

	Veggies veggies[];

	Cheese cheese;

	Pepperoni pepperoni;

	Clams clam;

	// interfaces
	public abstract void prepare();

	public void bake() {
		System.out.println("Bake for 25 minutes at 350");
	}

	public void cut() {
		System.out.println("Cutting the pizza into diagonal slices");
	}

	public void box() {
		System.out.println("Placing pizza in official PizzaStore box");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Dough getDough() {
		return dough;
	}

	public void setDough(Dough dough) {
		this.dough = dough;
	}

	public Sauce getSauce() {
		return sauce;
	}

	public void setSauce(Sauce sauce) {
		this.sauce = sauce;
	}

	public Veggies[] getVeggies() {
		return veggies;
	}

	public void setVeggies(Veggies[] veggies) {
		this.veggies = veggies;
	}

	public Cheese getCheese() {
		return cheese;
	}

	public void setCheese(Cheese cheese) {
		this.cheese = cheese;
	}

	public Pepperoni getPepperoni() {
		return pepperoni;
	}

	public void setPepperoni(Pepperoni pepperoni) {
		this.pepperoni = pepperoni;
	}

	public Clams getClam() {
		return clam;
	}

	public void setClam(Clams clam) {
		this.clam = clam;
	}

	@Override
	public String toString() {
		return "Pizza [name=" + name + ", dough=" + dough + ", sauce=" + sauce + ", veggies=" + Arrays.toString(veggies) + ", cheese=" + cheese
				+ ", pepperoni=" + pepperoni + ", clam=" + clam + "]";
	}

}
