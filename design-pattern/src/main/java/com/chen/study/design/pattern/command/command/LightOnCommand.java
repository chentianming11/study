package com.chen.study.design.pattern.command.command;

import com.chen.study.design.pattern.command.device.Light;
import lombok.AllArgsConstructor;

/**
 * 打开电灯命令对象
 * @author 陈添明
 * @date 2019/1/7
 */
@AllArgsConstructor
public class LightOnCommand implements Command {

    private Light light;

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
