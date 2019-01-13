package com.chen.study.design.pattern.command.command;

/**
 * @author 陈添明
 * @date 2019/1/8
 */
public class NoCommand implements Command {
    @Override
    public void execute() {
        System.out.println("空命令");
    }

    @Override
    public void undo() {

    }
}
