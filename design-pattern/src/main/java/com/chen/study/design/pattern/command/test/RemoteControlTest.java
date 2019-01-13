package com.chen.study.design.pattern.command.test;

import com.chen.study.design.pattern.command.command.*;
import com.chen.study.design.pattern.command.control.RemoteControl;
import com.chen.study.design.pattern.command.device.GarageDoor;
import com.chen.study.design.pattern.command.device.Light;
import com.chen.study.design.pattern.command.device.Stereo;

/**
 * @author 陈添明
 * @date 2019/1/7
 */
public class RemoteControlTest {

    public static void main(String[] args) {

        RemoteControl remoteControl = new RemoteControl();
        Light light = new Light();
        Stereo stereo = new Stereo();
        GarageDoor garageDoor = new GarageDoor();

        LightOnCommand lightOnCommand = new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);
        GarageDoorOpenCommand garageDoorOpenCommand = new GarageDoorOpenCommand(garageDoor);
        GarageDoorCloseCommand garageDoorCloseCommand = new GarageDoorCloseCommand(garageDoor);
        StereoOnWithCdCommand stereoOnWithCdCommand = new StereoOnWithCdCommand(stereo);
        StereoOffCommand stereoOffCommand = new StereoOffCommand(stereo);

        remoteControl.setCommand(0, lightOnCommand, lightOffCommand);
        remoteControl.setCommand(1, garageDoorOpenCommand, garageDoorCloseCommand);
        remoteControl.setCommand(2, stereoOnWithCdCommand, stereoOffCommand);

        System.out.println(remoteControl);

        remoteControl.onButtonPushed(0);
        remoteControl.onButtonPushed(1);
        remoteControl.onButtonPushed(2);

        remoteControl.offButtonPushed(0);
        remoteControl.offButtonPushed(1);
        remoteControl.offButtonPushed(2);
    }
}
