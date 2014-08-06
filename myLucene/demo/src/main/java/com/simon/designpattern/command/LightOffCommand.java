package com.simon.designpattern.command;
/**
 * @Author  : simon
 * @version : Apr 21, 2014 9:13:47 PM
 * 命令对象，持有命令的接收者，实现了命令（调用命令接收者对应的方法）
 *
 **/
public class LightOffCommand implements Command {

	Light light;

	public LightOffCommand(Light light) {
		this.light = light;
	}

	@Override
	public void execute() {
		light.off();
	}

	@Override
	public void undo() {
		light.on();
	}

}
