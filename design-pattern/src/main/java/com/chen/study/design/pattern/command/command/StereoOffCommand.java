package com.chen.study.design.pattern.command.command;

import com.chen.study.design.pattern.command.device.Stereo;

/**
 * 关闭音响
 * @author 陈添明
 * @date 2019/1/8
 */
public class StereoOffCommand implements Command{

    private Stereo stereo;

    public StereoOffCommand(Stereo stereo){
        this.stereo = stereo;
    }

    @Override
    public void execute(){
        stereo.off();
    }

    @Override
    public void undo() {
        stereo.on();
    }
}
