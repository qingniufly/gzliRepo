package com.simon.designpattern.statuscommand;
/**
 * @Author  : simon
 * @version : Apr 22, 2014 3:16:59 PM
 *　天花板吊扇
 **/
public class CeilingFan {

	public static final int HIGH = 3;
	public static final int MEDIUM = 2;
	public static final int LOW = 1;
	public static final int OFF = 0;
	String location;
	int speed;

	public CeilingFan(String location) {
		this.location = location;
		this.speed = OFF;
	}

	public void high() {
		speed = HIGH;
	}

	public void medium() {
		speed = MEDIUM;
	}

	public void low() {
		speed = LOW;
	}

	public void off() {
		speed = OFF;
	}

	public int getSpeed() {
		return speed;
	}

	@Override
	public String toString() {
		return "CeilingFan [location=" + location + ", speed=" + speed + "]";
	}

}
