package com.simon.designpattern.command;
/**
 * @Author  : simon
 * @version : Apr 21, 2014 9:13:47 PM
 * 命令对象，持有命令的接收者，实现了命令（调用命令接收者对应的方法）
 *
 **/
public class LightOnCommand implements Command {

	Light light;

	public LightOnCommand(Light light) {
		this.light = light;
	}

	@Override
	public void execute() {
		light.on();
	}

	@Override
	public void undo() {
		light.off();
	}

}
