package com.simon.designpattern.command;
/**
 * @Author  : simon
 * @version : Apr 21, 2014 9:17:31 PM
 *
 **/
public class Light {

	private String type;

	public Light(String type) {
		this.type = type;
	}

	public void on() {
		System.out.println(type + " Light On");
	}

	public void off() {
		System.out.println(type + " Light Off");
	}

}
