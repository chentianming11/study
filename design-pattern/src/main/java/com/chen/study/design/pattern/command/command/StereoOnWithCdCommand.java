package com.chen.study.design.pattern.command.command;

import com.chen.study.design.pattern.command.device.Stereo;

/**
 * cd模式 音量10 打开音响
 * @author 陈添明
 * @date 2019/1/8
 */
public class StereoOnWithCdCommand implements Command {

    private Stereo stereo;

    public StereoOnWithCdCommand(Stereo stereo){
        this.stereo = stereo;
    }

    @Override
    public void execute(){
        stereo.setCd();
        stereo.setVolume(10);
        stereo.on();
    }

    @Override
    public void undo() {
        stereo.off();
    }
}
