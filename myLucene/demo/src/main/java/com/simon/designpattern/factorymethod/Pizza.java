package com.simon.designpattern.factorymethod;

import java.util.ArrayList;
import java.util.List;

import com.simon.designpattern.abstractfactory.Cheese;
import com.simon.designpattern.abstractfactory.Clams;
import com.simon.designpattern.abstractfactory.Pepperoni;
import com.simon.designpattern.abstractfactory.Veggies;

public abstract class Pizza {

	// 名称
	String name;
	// 面团
	String dough;
	// 酱料
	String sauce;
	// 佐料
	List<String> toppings = new ArrayList<>();

	Veggies veggies[];

	Cheese cheese;

	Pepperoni pepperoni;

	Clams clam;

	// interfaces
	public void prepare() {
		System.out.println("Preparing " + name);
		System.out.println("Tossing dough...");
		System.out.println("Adding sauce...");
		System.out.println("Adding toppings: ");
		for (String topping : toppings) {
			System.out.println("	" + topping);
		}
	}

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

	public String getDough() {
		return dough;
	}

	public void setDough(String dough) {
		this.dough = dough;
	}

	public String getSauce() {
		return sauce;
	}

	public void setSauce(String sauce) {
		this.sauce = sauce;
	}

	public List<String> getToppings() {
		return toppings;
	}

	public void setToppings(List<String> toppings) {
		this.toppings = toppings;
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

}
