package com.chen.study.design.pattern.command.command;

import com.chen.study.design.pattern.command.device.Light;
import lombok.AllArgsConstructor;

/**
 * 关闭电灯命令对象
 * @author 陈添明
 * @date 2019/1/7
 */
@AllArgsConstructor
public class LightOffCommand implements Command {

    private Light light;

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
