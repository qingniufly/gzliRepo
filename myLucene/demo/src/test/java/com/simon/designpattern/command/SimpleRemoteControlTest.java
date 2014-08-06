package com.simon.designpattern.command;

import org.junit.Before;
import org.junit.Test;

import com.simon.designpattern.command.Light;
import com.simon.designpattern.command.LightOffCommand;
import com.simon.designpattern.command.LightOnCommand;
import com.simon.designpattern.command.SimpleRemoteControl;

/**
 * @Author  : simon
 * @version : Apr 22, 2014 10:49:27 AM
 *
 **/
public class SimpleRemoteControlTest {

	SimpleRemoteControl control;
	LightOnCommand lightOnCmd;
	LightOffCommand lightOffCmd;
	Light roomLight;
	Light livingLight;

	@Before
	public void setUp() {
		roomLight = new Light("Room Light");
		livingLight = new Light("Living Room Light");
		control = new SimpleRemoteControl();
	}

	@Test
	public void testButtonPress() {
		lightOnCmd = new LightOnCommand(roomLight);
		lightOffCmd = new LightOffCommand(roomLight);
		control.setCommand(lightOnCmd);
		control.buttonWasPressed();
		control.setCommand(lightOffCmd);
		control.buttonWasPressed();

		lightOnCmd = new LightOnCommand(livingLight);
		lightOffCmd = new LightOffCommand(livingLight);
		control.setCommand(lightOnCmd);
		control.buttonWasPressed();
		control.setCommand(lightOffCmd);
		control.buttonWasPressed();
	}

}
