package com.chen.study.design.pattern.command.test;

import com.chen.study.design.pattern.command.command.*;
import com.chen.study.design.pattern.command.control.RemoteControl;
import com.chen.study.design.pattern.command.device.GarageDoor;
import com.chen.study.design.pattern.command.device.Light;
import com.chen.study.design.pattern.command.device.Stereo;

/**
 * @author 陈添明
 * @date 2019/1/13
 */
public class MacroCommandTest {

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

        // 命令集合
        Command[] partyOn = {lightOnCommand, garageDoorOpenCommand, stereoOnWithCdCommand};
        Command[] partyOff = {lightOffCommand, garageDoorCloseCommand, stereoOffCommand};

        // 宏命令
        MacroCommand partyOnMacro = new MacroCommand(partyOn);
        MacroCommand partyOffMacro = new MacroCommand(partyOff);
        // 设置命令
        remoteControl.setCommand(0, partyOnMacro, partyOffMacro);

        System.out.println(remoteControl);

        System.out.println("---------开启宏命令---------");
        remoteControl.onButtonPushed(0);
        System.out.println("-----------关闭宏命令------------");
        remoteControl.offButtonPushed(0);


    }
}
