package com.simon.designpattern.statuscommand;

import org.junit.Before;
import org.junit.Test;

import com.simon.designpattern.command.SimpleRemoteControl;

/**
 * @Author  : simon
 * @version : Apr 22, 2014 10:49:27 AM
 *
 **/
public class StatusRemoteControlTest {

	SimpleRemoteControl control;
	CeilingFanHighCommand ceilingFanHighCmd;
	CeilingFan ceilingFan;

	@Before
	public void setUp() {
		ceilingFan = new CeilingFan("Living Room");
		control = new SimpleRemoteControl();
	}

	@Test
	public void testButtonPress() {
		ceilingFanHighCmd = new CeilingFanHighCommand(ceilingFan);
		control.setCommand(ceilingFanHighCmd);
		control.buttonWasPressed();
		System.out.println(ceilingFan);
		control.undoButtonPressed();
		System.out.println(ceilingFan);
	}

}
