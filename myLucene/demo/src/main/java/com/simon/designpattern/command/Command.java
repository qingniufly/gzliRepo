package com.simon.designpattern.command;
/**
 * @Author  : simon
 * @version : Apr 21, 2014 9:12:14 PM
 * 命令接口
 **/
public interface Command {

	public void execute();

	public void undo();

}
