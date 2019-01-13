package com.chen.study.design.pattern.command.command;

import com.chen.study.design.pattern.command.device.CeilingFan;

/**
 * @author 陈添明
 * @date 2019/1/13
 */
public abstract class AbstractCeilingFanCommand implements Command {
   protected  CeilingFan ceilingFan;
   protected int prevSpeed;

    public AbstractCeilingFanCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }
    @Override
    public void execute(){
        prevSpeed = ceilingFan.getSpeed();
        doExecute();
    }
    public abstract void doExecute();

    @Override
    public void undo(){
        if (prevSpeed == CeilingFan.HIGH){
            ceilingFan.high();
        } else if (prevSpeed == CeilingFan.MEDIUM){
            ceilingFan.mediun();
        } else if (prevSpeed == CeilingFan.LOW){
            ceilingFan.low();
        } else if (prevSpeed == CeilingFan.OFF){
            ceilingFan.off();
        }
    }
}
