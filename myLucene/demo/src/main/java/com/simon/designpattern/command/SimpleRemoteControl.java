package com.simon.designpattern.command;
/**
 * @Author  : simon
 * @version : Apr 22, 2014 10:27:30 AM
 *
 **/
public class SimpleRemoteControl {

	Command slot;

	public void setCommand(Command command) {
		this.slot = command;
	}

	public void buttonWasPressed() {
		slot.execute();
	}

	public void undoButtonPressed() {
		slot.undo();
	}

}
